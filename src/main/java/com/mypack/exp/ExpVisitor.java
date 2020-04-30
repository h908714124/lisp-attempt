package com.mypack.exp;

public interface ExpVisitor<R> {

    R visitEmptySexp(EmptySexp emptySexp);

    R visitSexp(Sexp sexp);

    R visitSymbol(Symbol symbol);
}
