package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.AsSexp;
import com.mypack.util.IsLambda;

import java.util.ArrayList;
import java.util.List;

public class Eval implements ExpVisitor<Exp> {

    @Override
    public Exp visitSexp(Sexp sexp) {
        if (sexp.head() instanceof Sexp && IsLambda.test(((Sexp) sexp.head()).head())) {
            List<Exp> lambdaTail = ((Sexp) sexp.head()).tail();
            return Lambda.createLambda(AsSexp.get(lambdaTail.get(0)), lambdaTail.get(1), sexp.tail());
        }
        boolean isLambda = IsLambda.test(sexp.head());
        Exp newHead = sexp.head().accept(this);
        List<Exp> result = new ArrayList<>();
        List<Exp> tail = sexp.tail();
        for (int i = 0; i < tail.size(); i++) {
            Exp exp = tail.get(i);
            if (isLambda && i == 0) {
                result.add(exp);
            } else {
                result.add(exp.accept(this));
            }
        }
        return new Sexp(newHead, result);
    }

    static List<Exp> iterEval(Exp exp) {
        return iterEval(exp, 100);
    }

    static List<Exp> iterEval(Exp exp, int max) {
        List<Exp> result = new ArrayList<>(max);
        int n = 0;
        String s;
        do {
            s = exp.toString();
            Exp newExp = exp.accept(new Eval());
            result.add(exp);
            n += 1;
            exp = newExp;
        } while (!exp.toString().equals(s) && n < max);
        return result;
    }

    @Override
    public Exp visitSymbol(Symbol symbol) {
        return symbol;
    }
}
