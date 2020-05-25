package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

public class IsSexp implements ExpVisitor<Boolean> {

    private static final IsSexp INSTANCE = new IsSexp();

    public static boolean test(Exp exp) {
        return exp.accept(INSTANCE);
    }

    @Override
    public Boolean visitSexp(Sexp sexp) {
        return true;
    }

    @Override
    public Boolean visitSymbol(Symbol symbol) {
        return false;
    }
}
