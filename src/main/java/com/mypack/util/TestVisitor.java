package com.mypack.util;

import com.mypack.exp.EmptySexp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.exp.Value;

abstract class TestVisitor implements ExpVisitor<Boolean> {

    @Override
    public Boolean visitEmptySexp(EmptySexp emptySexp) {
        return false;
    }

    @Override
    public Boolean visitSexp(Sexp sexp) {
        return false;
    }

    @Override
    public Boolean visitValue(Value value) {
        return false;
    }

    @Override
    public Boolean visitSymbol(Symbol symbol) {
        return false;
    }
}
