package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

public class IsLambda implements ExpVisitor<Boolean> {

    private static final IsLambda INSTANCE = new IsLambda();

    public static boolean test(Exp exp) {
        return exp.accept(INSTANCE);
    }

    @Override
    public Boolean visitSexp(Sexp sexp) {
        return false;
    }

    @Override
    public Boolean visitSymbol(Symbol symbol) {
        return symbol.value().equals("lambda");
    }
}
