package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.parser.LispParser;
import com.mypack.util.AsSexp;
import com.mypack.util.AsSymbol;
import com.mypack.util.DefExpression;
import com.mypack.util.FindNumbers;
import com.mypack.util.IsDefExpression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Environment {

    private final Map<Symbol, Exp> definitions = new LinkedHashMap<>();

    public Exp eval(String exp) {
        return eval(LispParser.parse(exp));
    }

    public Exp eval(Exp exp) {
        List<Exp> result = iterEval(exp, 10000);
        return result.get(result.size() - 1);
    }

    public void load(List<Exp> expressions) {
        for (Exp expression : expressions) {
            load(expression);
        }
    }

    private void load(Exp exp) {
        Optional<DefExpression> defExpression = IsDefExpression.test(exp);
        if (defExpression.isPresent()) {
            Exp definition = resolve(defExpression.get().definition());
            definitions.put(defExpression.get().name(), definition);
        }
    }

    public List<Exp> iterEval(Exp unresolvedExp, int max) {
        Exp exp = resolve(unresolvedExp);
        return internalIterEval(exp, max);
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

    private static List<Exp> internalIterEval(Exp exp, int max) {
        Eval eval = new Eval();
        List<Exp> result = new ArrayList<>(max);
        int n = 0;
        String s;
        do {
            s = exp.toString();
            Exp newExp = exp.accept(eval);
            result.add(exp);
            n += 1;
            exp = newExp;
        } while (!exp.toString().equals(s) && n <= max);
        return result;
    }
}
