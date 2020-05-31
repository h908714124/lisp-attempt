package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.parser.LispParser;
import com.mypack.util.FindNumbers;
import com.mypack.util.IsDefExpression;
import com.mypack.util.IsDefnExpression;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Environment {

    private final Map<Symbol, Exp> definitions = new LinkedHashMap<>();

    private final PrintStream out;

    private boolean printing;

    public Environment(PrintStream out) {
        this.out = out;
    }

    public Environment() {
        this(System.out);
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
            Exp definition = resolve(defExpression.definition());
            definitions.put(defExpression.name(), definition);
        });
        IsDefnExpression.test(exp).ifPresent(defnExpression -> {
            LambdaExpression lambda = new LambdaExpression(defnExpression.params(), defnExpression.body());
            Exp definition = resolve(lambda.toExp());
            definitions.put(defnExpression.name(), definition);
        });
    }

    public Exp eval(String unresolvedExp, int maxSteps) {
        return eval(LispParser.parse(unresolvedExp), maxSteps);
    }

    private Exp eval(Exp unresolvedExp, int maxSteps) {
        if (printing) {
            out.println(unresolvedExp.toString());
        }
        Exp exp = resolve(unresolvedExp);
        if (printing) {
            out.println(exp.toString());
        }
        Exp result = internalIterEval(exp, maxSteps);
        if (printing) {
            out.flush();
        }
        return result;
    }

    private Exp resolve(Exp exp) {
        Exp result = exp;
        Set<Symbol> numbers = FindNumbers.search(exp);
        for (Symbol number : numbers) {
            LambdaExpression lambda = new LambdaExpression(ParamBlock.create(number), result);
            result = lambda.apply(churchNumeral(Integer.parseInt(number.value())), definitions.keySet());
        }
        for (Map.Entry<Symbol, Exp> e : definitions.entrySet()) {
            LambdaExpression lambda = new LambdaExpression(ParamBlock.create(e.getKey()), result);
            result = lambda.apply(e.getValue(), definitions.keySet());
        }
        return result;
    }

    static Exp churchNumeral(int n) {
        Symbol x = Symbol.of("x");
        Symbol f = Symbol.of("f");
        Exp result = x;
        for (int i = 0; i < n; i++) {
            result = Sexp.create(f, Collections.singletonList(result));
        }
        return new LambdaExpression(ParamBlock.create(f, x), result).toExp();
    }

    private Exp internalIterEval(Exp exp, int max) {
        Eval eval = new Eval();
        int n = 0;
        String s;
        do {
            s = exp.toString();
            Exp newExp = exp.accept(eval);
            n += 1;
            exp = newExp;
            if (printing) {
                out.println(exp.toString());
            }
        } while (!exp.toString().equals(s) && n < max);
        return exp;
    }

    public void setPrinting(boolean printing) {
        this.printing = printing;
    }
}
