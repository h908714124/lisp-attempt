package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

public class AsSymbol implements ExpVisitor<Symbol> {

    private static final AsSymbol INSTANCE = new AsSymbol();

    public static Symbol get(Exp exp) {
        return exp.accept(INSTANCE);
    }

    @Override
    public Symbol visitSexp(Sexp sexp) {
        throw new IllegalArgumentException("symbol expected but found sexp " + sexp);
    }

    @Override
    public Symbol visitSymbol(Symbol symbol) {
        return symbol;
    }
}
