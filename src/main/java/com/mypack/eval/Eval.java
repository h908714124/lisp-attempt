package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.IsLambdaExpression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Eval implements ExpVisitor<Exp> {

    private final Map<Symbol, Exp> definitions;

    public Eval(Map<Symbol, Exp> definitions) {
        this.definitions = definitions;
    }

    @Override
    public Exp visitSexp(Sexp sexp) {
        if (IsLambdaExpression.test(sexp.head())) {
            LambdaExpression lambda = LambdaExpression.create(sexp.head());
            return lambda.betaReduction(sexp.tail());
        }
        boolean isLambda = IsLambdaExpression.test(sexp);
        Exp newHead = sexp.head().accept(this);
        List<Exp> result = new ArrayList<>(sexp.tail().size());
        List<? extends Exp> tail = sexp.tail();
        for (int i = 0; i < tail.size(); i++) {
            Exp exp = tail.get(i);
            if (isLambda && i == 0) { // Is exp a variableList?
                result.add(exp);
            } else {
                result.add(exp.accept(this));
            }
        }
        return Sexp.create(newHead, result);
    }

    static List<Exp> iterEval(Exp exp) {
        return iterEval(exp, 100);
    }

    static List<Exp> iterEval(Exp exp, int max) {
        return iterEval(exp, max, Collections.emptyMap());
    }

    static List<Exp> iterEval(Exp exp, int max, Map<Symbol, Exp> definitions) {
        List<Exp> result = new ArrayList<>(max);
        int n = 0;
        String s;
        do {
            s = exp.toString();
            Exp newExp = exp.accept(new Eval(definitions));
            result.add(exp);
            n += 1;
            exp = newExp;
        } while (!exp.toString().equals(s) && n <= max);
        return result;
    }

    @Override
    public Exp visitSymbol(Symbol symbol) {
        Exp definition = definitions.get(symbol);
        if (definition != null) {
            // TODO alpha
            return definition;
        }
        return symbol;
    }
}
