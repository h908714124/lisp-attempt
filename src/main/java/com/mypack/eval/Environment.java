package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.Symbol;
import com.mypack.parser.LispParser;
import com.mypack.util.AsSexp;
import com.mypack.util.AsSymbol;
import com.mypack.util.IsDefExpression;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Environment {

    private final Map<Symbol, Exp> definitions = new HashMap<>();

    public Exp eval(String exp) {
        return eval(LispParser.parse(exp));
    }

    public Exp eval(Exp exp) {
        List<Exp> result = iterEval(exp, 100);
        return result.get(result.size() - 1);
    }

    public void load(List<Exp> expressions) {
        for (Exp expression : expressions) {
            iterEval(expression, 100);
        }
    }

    public List<Exp> iterEval(String exp, int max) {
        return iterEval(LispParser.parse(exp), max);
    }

    public List<Exp> iterEval(Exp exp, int max) {
        if (IsDefExpression.test(exp)) {
            List<? extends Exp> sexp = AsSexp.get(exp).asList();
            if (sexp.size() != 3) {
                throw new IllegalArgumentException("Expecting 2 arguments but found " + (sexp.size() - 1));
            }
            Symbol symbol = AsSymbol.get(sexp.get(1));
            List<Exp> results = Eval.iterEval(sexp.get(2), max, definitions);
            definitions.put(symbol, results.get(results.size() - 1));
            return results;
        }
        return Eval.iterEval(exp, max, definitions);
    }
}
