package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

public class AsSexp implements ExpVisitor<Sexp> {

    private static final AsSexp INSTANCE = new AsSexp();

    public static Sexp get(Exp exp) {
        return exp.accept(INSTANCE);
    }

    @Override
    public Sexp visitSexp(Sexp sexp) {
        return sexp;
    }

    @Override
    public Sexp visitSymbol(Symbol symbol) {
        throw new IllegalArgumentException("sexp expected but found symbol " + symbol);
    }
}
