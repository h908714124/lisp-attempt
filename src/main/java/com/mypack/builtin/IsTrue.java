package com.mypack.builtin;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import static com.mypack.builtin.Constants.K;
import static com.mypack.builtin.Constants.TRUE;

public class IsTrue implements ExpVisitor<Boolean, Void> {

    private IsTrue() {
    }

    private static final IsTrue INSTANCE = new IsTrue();

    public static boolean test(Exp exp) {
        return exp.accept(INSTANCE, null);
    }

    @Override
    public Boolean visitSexp(Sexp sexp, Void _null) {
        return false;
    }

    @Override
    public Boolean visitSymbol(Symbol symbol, Void _null) {
        return symbol.equals(K) || symbol.equals(TRUE);
    }

    @Override
    public Boolean visitParamBlock(ParamBlock paramBlock) {
        return false;
    }
}
