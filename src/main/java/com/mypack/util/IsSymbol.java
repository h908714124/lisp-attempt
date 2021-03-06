package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import java.util.regex.Pattern;

public class IsSymbol implements ExpVisitor<Boolean, Void> {

    private static final IsSymbol INSTANCE = new IsSymbol();

    public static boolean test(Exp exp) {
        return exp.accept(INSTANCE, null);
    }

    public static boolean test(Exp exp, String test) {
        return exp.accept(INSTANCE, null) && AsSymbol.get(exp).value().equals(test);
    }

    public static boolean test(Exp exp, Pattern test) {
        return exp.accept(INSTANCE, null) && test.matcher(AsSymbol.get(exp).value()).matches();
    }

    @Override
    public Boolean visitSexp(Sexp sexp, Void _null) {
        return false;
    }

    @Override
    public Boolean visitSymbol(Symbol symbol, Void _null) {
        return true;
    }

    @Override
    public Boolean visitParamBlock(ParamBlock paramBlock) {
        return false;
    }
}
