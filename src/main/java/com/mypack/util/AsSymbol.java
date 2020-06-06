package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

public class AsSymbol implements ExpVisitor<Symbol, Void> {

    private static final AsSymbol INSTANCE = new AsSymbol();

    public static Symbol get(Exp exp) {
        return exp.accept(INSTANCE, null);
    }

    @Override
    public Symbol visitSexp(Sexp sexp, Void _null) {
        throw new IllegalArgumentException("symbol expected but found sexp " + sexp);
    }

    @Override
    public Symbol visitSymbol(Symbol symbol, Void _null) {
        return symbol;
    }

    @Override
    public Symbol visitParamBlock(ParamBlock paramBlock) {
        throw new IllegalArgumentException("symbol expected but found param block " + paramBlock);
    }
}
