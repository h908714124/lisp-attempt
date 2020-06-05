package com.mypack.exp;

public interface ExpVisitor<R, P> {

    R visitSexp(Sexp sexp, P p);

    R visitSymbol(Symbol symbol);

    R visitParamBlock(ParamBlock paramBlock);
}
