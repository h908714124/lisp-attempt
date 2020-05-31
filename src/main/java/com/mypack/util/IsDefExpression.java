package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import java.util.Optional;

public class IsDefExpression implements ExpVisitor<Optional<DefExpression>> {

    private static final IsDefExpression INSTANCE = new IsDefExpression();

    public static Optional<DefExpression> test(Exp exp) {
        return exp.accept(INSTANCE);
    }

    @Override
    public Optional<DefExpression> visitSexp(Sexp sexp) {
        if (!IsDefSymbol.test(sexp.head())) {
            return Optional.empty();
        }
        if (sexp.size() != 3) {
            throw new IllegalArgumentException("Expecting 2 arguments but found " + (sexp.size() - 1));
        }
        Symbol name = AsSymbol.get(sexp.get(1));
        Exp definition = sexp.get(2);
        return Optional.of(new DefExpression(name, definition));
    }

    @Override
    public Optional<DefExpression> visitSymbol(Symbol symbol) {
        return Optional.empty();
    }

    @Override
    public Optional<DefExpression> visitParamBlock(ParamBlock paramBlock) {
        return Optional.empty();
    }
}
