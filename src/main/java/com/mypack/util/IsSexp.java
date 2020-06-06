package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

public class IsSexp implements ExpVisitor<Boolean, Void> {

    private static final IsSexp INSTANCE = new IsSexp();

    public static boolean test(Exp exp) {
        return exp.accept(INSTANCE, null);
    }

    @Override
    public Boolean visitSexp(Sexp sexp, Void _null) {
        return true;
    }

    @Override
    public Boolean visitSymbol(Symbol symbol, Void _null) {
        return false;
    }

    @Override
    public Boolean visitParamBlock(ParamBlock paramBlock) {
        return false;
    }
}
