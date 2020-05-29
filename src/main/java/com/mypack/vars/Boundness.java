package com.mypack.vars;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.AsSexp;
import com.mypack.util.IsLambdaExpression;

public class Boundness implements ExpVisitor<Boolean> {

    private final Symbol test;

    private Boundness(Symbol test) {
        this.test = test;
    }

    public static boolean test(Exp exp, Symbol symbol) {
        return exp.accept(new Boundness(symbol));
    }

    @Override
    public Boolean visitSexp(Sexp sexp) {
        if (IsLambdaExpression.test(sexp)) {
            Sexp symbols = AsSexp.get(sexp.tail().get(0));
            for (Exp symbol : symbols.asList()) {
                if (symbol.equals(test)) {
                    return true;
                }
            }
            return sexp.tail().get(1).accept(this);
        }
        for (Exp exp : sexp.asList()) {
            if (exp.accept(this)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean visitSymbol(Symbol symbol) {
        return false;
    }
}
