package com.mypack.builtin;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

class Splicing implements ExpVisitor<Exp, Sexp> {

    private Splicing() {
    }

    private static final Splicing INSTANCE = new Splicing();

    static Splicing get() {
        return INSTANCE;
    }

    @Override
    public Exp visitSexp(Sexp sexp, Sexp outer) {
        return Sexp.create(sexp.get(0), sexp.get(1), outer.subList(2));
    }

    @Override
    public Exp visitSymbol(Symbol symbol, Sexp outer) {
        return Sexp.create(symbol, outer.subList(2));
    }

    @Override
    public Exp visitParamBlock(ParamBlock paramBlock) {
        throw new IllegalArgumentException();
    }
}
