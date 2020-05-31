package com.mypack.exp;

public interface ExpVisitor<R> {

    R visitSexp(Sexp sexp);

    R visitSymbol(Symbol symbol);

    R visitParamBlock(ParamBlock paramBlock);
}
