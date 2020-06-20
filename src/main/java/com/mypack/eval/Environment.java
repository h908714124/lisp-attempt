package com.mypack.eval;

import com.mypack.builtin.EvalContext;
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
import java.util.Optional;
import java.util.Set;

public class Environment implements ExpVisitor<Exp, Void> {

    private final Map<Symbol, Exp> definitions = new LinkedHashMap<>();

    private final PrintStream out;

    private boolean printing;

    public Environment(PrintStream out) {
        this.out = out;
    }

    public Environment() {
        this(new PrintStream(OutputStream.nullOutputStream()));
    }

    public Exp eval(String exp) {
        return eval(LispParser.parse(exp));
    }

    public Exp eval(Exp exp) {
        return eval(exp, 10000);
    }

    public void load(Path path) {
        try {
            String data = Files.readString(path);
            List<Exp> expressions = LispParser.parseList(data);
            for (Exp expression : expressions) {
                eval(expression);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Exp lookup(String symbol) {
        return lookup(Symbol.of(symbol));
    }

    public Exp lookup(Symbol symbol) {
        return definitions.get(symbol);
    }

    public Exp eval(String unresolvedExp, int maxSteps) {
        return eval(LispParser.parse(unresolvedExp), maxSteps);
    }

    public Exp eval(Exp exp, int maxSteps) {
        Freshness.test(exp).ifPresent(symbol -> {
            throw new IllegalArgumentException("Non-fresh symbol in input: " + symbol);
        });
        Exp result = internalIterEval(exp, maxSteps);
        if (printing) {
            out.flush();
        }
        return result;
    }

    private Optional<Exp> checkDef(Exp exp) {
        return IsDefExpression.test(exp).map(defExpression -> {
            Exp definition = defExpression.definition();
            Symbol name = defExpression.name();
            definitions.put(name, definition);
            return definition;
        }).or(() -> IsDefnExpression.test(exp).map(defnExpression -> {
            LambdaExpression lambda = new LambdaExpression(defnExpression.params(), defnExpression.body());
            definitions.put(defnExpression.name(), lambda.toExp());
            return lambda.toExp();
        }));
    }

    private Exp internalIterEval(Exp exp, int max) {
        Optional<Exp> definition = checkDef(exp);
        if (definition.isPresent()) {
            return definition.get();
        }
        int n = 0;
        while (n < max) {
            if (printing) {
                out.println(exp.toString());
            }
            Exp newExp = exp.accept(Eval.get(), new EvalContext(this, Set.of()));
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
    public Exp visitSexp(Sexp sexp, Void _null) {
        return sexp;
    }

    // lookup
    @Override
    public Exp visitSymbol(Symbol symbol, Void _null) {
        return lookup(symbol);
    }

    // lookup
    @Override
    public Exp visitParamBlock(ParamBlock paramBlock) {
        return paramBlock;
    }
}
