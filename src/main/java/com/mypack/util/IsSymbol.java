package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

public class IsSymbol implements ExpVisitor<Boolean> {

    private static final IsSymbol INSTANCE = new IsSymbol();

    public static boolean test(Exp exp) {
        return exp.accept(INSTANCE);
    }

    @Override
    public Boolean visitSexp(Sexp sexp) {
        return false;
    }

    @Override
    public Boolean visitSymbol(Symbol symbol) {
        return true;
    }

    @Override
    public Boolean visitParamBlock(ParamBlock paramBlock) {
        return false;
    }
}
