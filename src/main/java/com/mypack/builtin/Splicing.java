package com.mypack.builtin;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import java.util.ArrayList;
import java.util.List;

class Splicing implements ExpVisitor<Sexp, Sexp> {

    private final int cut;

    Splicing(int cut) {
        this.cut = cut;
    }

    @Override
    public Sexp visitSexp(Sexp sexp, Sexp outer) {
        List<Exp> result = new ArrayList<>(sexp.size() + outer.size() - cut);
        result.addAll(sexp.asList());
        result.addAll(outer.subList(cut));
        return Sexp.create(result);
    }

    @Override
    public Sexp visitSymbol(Symbol symbol, Sexp outer) {
        return Sexp.create(symbol, outer.subList(cut));
    }

    @Override
    public Sexp visitParamBlock(ParamBlock paramBlock) {
        throw new IllegalArgumentException();
    }
}
