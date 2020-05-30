package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.Symbol;
import com.mypack.parser.LispParser;
import com.mypack.util.AsSexp;
import com.mypack.util.AsSymbol;
import com.mypack.util.IsDefExpression;
import com.mypack.vars.AnalysisResult;
import com.mypack.vars.AnalysisVisitor;
import com.mypack.vars.BetaVisitor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Environment {

    private final Map<Symbol, Exp> definitions = new LinkedHashMap<>();

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

    public List<Exp> iterEval(Exp unresolvedExp, int max) {
        Exp exp = resolve(unresolvedExp);
        if (IsDefExpression.test(exp)) {
            List<? extends Exp> sexp = AsSexp.get(exp).asList();
            if (sexp.size() != 3) {
                throw new IllegalArgumentException("Expecting 2 arguments but found " + (sexp.size() - 1));
            }
            Symbol symbol = AsSymbol.get(sexp.get(1));
            List<Exp> results = internalIterEval(sexp.get(2), max);
            definitions.put(symbol, results.get(results.size() - 1));
            return results;
        }
        return internalIterEval(exp, max);
    }

    private Exp resolve(Exp exp) {
        for (Map.Entry<Symbol, Exp> e : definitions.entrySet()) {
            exp = resolve(exp, e.getKey());
        }
        return exp;
    }

    private Exp resolve(Exp exp, Symbol symbol) {
        AnalysisResult result = AnalysisVisitor.analyse(exp);
        Exp definition = definitions.get(symbol);
        Set<Symbol> reservedSet = LambdaExpression.union(result.bound(), definitions.keySet());
        for (Symbol reservedSymbol : result.bound()) {
            Symbol alternative = LambdaExpression.findAlternative(reservedSymbol, reservedSet);
            definition = definition.accept(new BetaVisitor(reservedSymbol, alternative));
        }
        return exp.accept(new BetaVisitor(symbol, definition));
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
