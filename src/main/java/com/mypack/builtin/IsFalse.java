package com.mypack.builtin;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import static com.mypack.builtin.Constants.FALSE;
import static com.mypack.builtin.Constants.ZERO;

public class IsFalse implements ExpVisitor<Boolean, Void> {

    private IsFalse() {
    }

    private static final IsFalse INSTANCE = new IsFalse();

    public static boolean test(Exp exp) {
        return exp.accept(INSTANCE, null);
    }

    @Override
    public Boolean visitSexp(Sexp sexp, Void _null) {
        return false;
    }

    @Override
    public Boolean visitSymbol(Symbol symbol, Void _null) {
        return symbol.equals(FALSE) || symbol.equals(ZERO);
    }

    @Override
    public Boolean visitParamBlock(ParamBlock paramBlock) {
        return false;
    }
}
