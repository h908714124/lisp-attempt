package com.mypack.util;

import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

final class IsSpecificSymbolVisitor implements ExpVisitor<Boolean> {

    private final Symbol test;

    IsSpecificSymbolVisitor(Symbol test) {
        this.test = test;
    }

    @Override
    public final Boolean visitSexp(Sexp sexp) {
        return false;
    }

    @Override
    public final Boolean visitSymbol(Symbol symbol) {
        return symbol.equals(test);
    }

    @Override
    public Boolean visitParamBlock(ParamBlock paramBlock) {
        return false;
    }
}
