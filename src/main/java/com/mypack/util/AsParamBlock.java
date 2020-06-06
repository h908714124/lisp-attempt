package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

public class AsParamBlock implements ExpVisitor<ParamBlock, Void> {

    private static final AsParamBlock INSTANCE = new AsParamBlock();

    public static ParamBlock get(Exp exp) {
        return exp.accept(INSTANCE, null);
    }

    @Override
    public ParamBlock visitSexp(Sexp sexp, Void _null) {
        throw new IllegalArgumentException("param block expected but found sexp " + sexp);
    }

    @Override
    public ParamBlock visitSymbol(Symbol symbol, Void _null) {
        throw new IllegalArgumentException("param block expected but found symbol " + symbol);
    }

    @Override
    public ParamBlock visitParamBlock(ParamBlock paramBlock) {
        return paramBlock;
    }
}
