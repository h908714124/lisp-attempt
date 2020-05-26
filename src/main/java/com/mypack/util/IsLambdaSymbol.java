package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

class IsLambdaSymbol implements ExpVisitor<Boolean> {

    private static final IsLambdaSymbol INSTANCE = new IsLambdaSymbol();

    static boolean test(Exp exp) {
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
