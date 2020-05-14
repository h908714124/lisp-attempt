package com.mypack.util;

import com.mypack.exp.EmptySexp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.exp.Value;

abstract class DefaultVisitor<E> implements ExpVisitor<E> {

    final String message;

    DefaultVisitor(String message) {
        this.message = message;
    }

    @Override
    public E visitEmptySexp(EmptySexp emptySexp) {
        throw new IllegalArgumentException(message + " but found empty sexp");
    }

    @Override
    public E visitSexp(Sexp sexp) {
        throw new IllegalArgumentException(message + " but found sexp " + sexp);
    }

    @Override
    public E visitValue(Value value) {
        throw new IllegalArgumentException(message + " but found value " + value);
    }

    @Override
    public E visitSymbol(Symbol symbol) {
        throw new IllegalArgumentException(message + " but found symbol " + symbol);
    }
}
