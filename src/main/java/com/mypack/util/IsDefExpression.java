package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

public class IsDefExpression implements ExpVisitor<Boolean> {

    private static final IsDefExpression INSTANCE = new IsDefExpression();

    public static boolean test(Exp exp) {
        return exp.accept(INSTANCE);
    }

    @Override
    public Boolean visitSexp(Sexp sexp) {
        return IsDefSymbol.test(sexp.head());
    }

    @Override
    public Boolean visitSymbol(Symbol symbol) {
        return false;
    }
}
