package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import java.util.Optional;

public class IsDefExpression implements ExpVisitor<Optional<DefExpression>, Void> {

    private static final IsDefExpression INSTANCE = new IsDefExpression();

    private static final IsSpecificSymbolVisitor IS_DEF_SYMBOL = new IsSpecificSymbolVisitor(Symbol.of("def"));

    public static Optional<DefExpression> test(Exp exp) {
        return exp.accept(INSTANCE, null);
    }

    @Override
    public Optional<DefExpression> visitSexp(Sexp sexp, Void _null) {
        if (!sexp.head().accept(IS_DEF_SYMBOL, null)) {
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
    public Optional<DefExpression> visitSymbol(Symbol symbol, Void _null) {
        return Optional.empty();
    }

    @Override
    public Optional<DefExpression> visitParamBlock(ParamBlock paramBlock) {
        return Optional.empty();
    }
}
