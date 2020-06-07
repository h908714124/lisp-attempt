package com.mypack.builtin;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import java.util.ArrayList;
import java.util.List;

class HeadSplicing implements ExpVisitor<Sexp, Sexp> {

    // how many parts to remove from outer to make room for the new head
    private final int cut;

    private static final HeadSplicing[] CACHE = new HeadSplicing[10];

    private HeadSplicing(int cut) {
        this.cut = cut;
    }

    static HeadSplicing get(int cut) {
        if (CACHE[cut] == null) {
            CACHE[cut] = new HeadSplicing(cut);
        }
        return CACHE[cut];
    }

    @Override
    public Sexp visitSexp(Sexp head, Sexp outer) {
        List<Exp> result = new ArrayList<>(head.size() + outer.size() - cut);
        result.addAll(head.asList());
        result.addAll(outer.subList(cut));
        return Sexp.create(result);
    }

    @Override
    public Sexp visitSymbol(Symbol head, Sexp outer) {
        return Sexp.create(head, outer.subList(cut));
    }

    @Override
    public Sexp visitParamBlock(ParamBlock paramBlock) {
        throw new IllegalArgumentException();
    }
}
