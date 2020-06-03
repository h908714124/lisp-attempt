package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.IsDefExpression;
import com.mypack.util.IsDefnExpression;
import com.mypack.vars.Freshness;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class Environment implements ExpVisitor<Exp> {

    private final Map<Symbol, Exp> definitions = new LinkedHashMap<>();

    static final Pattern NUMBER_PATTERN = Pattern.compile("0|[-]?[1-9]\\d*");

    private final PrintStream out;

    private boolean printing;

    private final Map<Exp, Exp> substitutionMap = new LinkedHashMap<>();

    public Environment(PrintStream out) {
        this.out = out;
    }

    public Environment() {
        this(new PrintStream(OutputStream.nullOutputStream()));
    }

    public Exp eval(String exp) {
        return eval(LispParser.parse(exp), 10000);
    }


    public void load(Path path) {
        try {
            String data = Files.readString(path);
            List<Exp> expressions = LispParser.parseList(data);
            for (Exp expression : expressions) {
                load(expression);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void load(Exp exp) {
        IsDefExpression.test(exp).ifPresent(defExpression -> {
            Exp definition = defExpression.definition();
            definitions.put(defExpression.name(), definition);
        });
        IsDefnExpression.test(exp).ifPresent(defnExpression -> {
            LambdaExpression lambda = new LambdaExpression(defnExpression.params(), defnExpression.body());
            definitions.put(defnExpression.name(), lambda.toExp());
        });
    }

    public Exp lookup(String symbol) {
        return lookup(Symbol.of(symbol));
    }

    public Exp lookup(Exp exp) {
        return exp.accept(this);
    }

    Exp lookup(Symbol symbol) {
        Exp definition = definitions.get(symbol);
        if (definition != null) {
            return definition;
        }
        if (NUMBER_PATTERN.matcher(symbol.value()).matches()) {
            return Environment.churchNumeral(Integer.parseInt(symbol.value()));
        }
        return null;
    }

    public Exp eval(String unresolvedExp, int maxSteps) {
        return eval(LispParser.parse(unresolvedExp), maxSteps);
    }

    private Exp eval(Exp exp, int maxSteps) {
        Freshness.test(exp).ifPresent(symbol -> {
            throw new IllegalArgumentException("Non-fresh symbol in input: " + symbol);
        });
        Exp result = internalIterEval(exp, maxSteps);
        if (printing) {
            out.flush();
        }
        return result;
    }

    static Exp churchNumeral(int n) {
        Symbol x = Symbol.of("x");
        Symbol f = Symbol.of("f");
        Exp result = x;
        for (int i = 0; i < n; i++) {
            result = Sexp.create(f, result);
        }
        return new LambdaExpression(ParamBlock.create(f, x), result).toExp();
    }

    private Exp internalIterEval(Exp exp, int max) {
        Eval eval = new Eval(this);
        int n = 0;
        while (n < max) {
            if (printing) {
                out.println(exp.toString());
            }
            Exp newExp = exp.accept(eval);
            if (newExp == exp) {
                break;
            }
            n += 1;
            exp = newExp;
        }
        return exp;
    }

    public void setPrinting(boolean printing) {
        this.printing = printing;
    }

    // lookup
    @Override
    public Exp visitSexp(Sexp sexp) {
        return sexp;
    }

    // lookup
    @Override
    public Exp visitSymbol(Symbol symbol) {
        return lookup(symbol);
    }

    // lookup
    @Override
    public Exp visitParamBlock(ParamBlock paramBlock) {
        return paramBlock;
    }

    public Set<Symbol> keySet() {
        return definitions.keySet();
    }

    public void addSubstitution(Exp exp1, Exp exp2) {
        substitutionMap.put(exp1, exp2);
    }

    public Set<Map.Entry<Exp, Exp>> substitutionEntries() {
        return substitutionMap.entrySet();
    }
}
