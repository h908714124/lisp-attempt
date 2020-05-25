package com.mypack.util;

import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

abstract class CastingVisitor<E> implements ExpVisitor<E> {

    final String message;

    CastingVisitor(String message) {
        this.message = message;
    }

    @Override
    public E visitSexp(Sexp sexp) {
        throw new IllegalArgumentException(message + " but found sexp " + sexp);
    }

    @Override
    public E visitSymbol(Symbol symbol) {
        throw new IllegalArgumentException(message + " but found symbol " + symbol);
    }
}
