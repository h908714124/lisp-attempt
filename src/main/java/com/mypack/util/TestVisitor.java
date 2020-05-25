package com.mypack.util;

import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

abstract class TestVisitor implements ExpVisitor<Boolean> {

    @Override
    public Boolean visitSexp(Sexp sexp) {
        return false;
    }

    @Override
    public Boolean visitSymbol(Symbol symbol) {
        return false;
    }
}
