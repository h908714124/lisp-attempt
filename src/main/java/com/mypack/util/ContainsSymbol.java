package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

public class ContainsSymbol implements ExpVisitor<Boolean> {

    private final Symbol test;

    private ContainsSymbol(Symbol test) {
        this.test = test;
    }

    public static boolean test(Symbol test, Exp exp) {
        return exp.accept(new ContainsSymbol(test));
    }

    @Override
    public Boolean visitSexp(Sexp sexp) {
        if (sexp.head().accept(this)) {
            return true;
        }
        for (Exp exp : sexp.tail()) {
            if (exp.accept(this)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean visitSymbol(Symbol symbol) {
        return symbol.equals(test);
    }
}
