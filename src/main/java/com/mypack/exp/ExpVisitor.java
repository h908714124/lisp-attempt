package com.mypack.exp;

public interface ExpVisitor<R> {

    R visitEmptySexp(EmptySexp emptySexp);

    R visitSexp(Sexp sexp);

    R visitValue(Value value);

    R visitSymbol(Symbol symbol);
}
