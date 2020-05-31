package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import java.util.Optional;

public class IsDefnExpression implements ExpVisitor<Optional<DefnExpression>> {

    private static final IsDefnExpression INSTANCE = new IsDefnExpression();

    private static final IsSpecificSymbolVisitor IS_DEFN_SYMBOL = new IsSpecificSymbolVisitor(Symbol.of("defn"));

    public static Optional<DefnExpression> test(Exp exp) {
        return exp.accept(INSTANCE);
    }

    @Override
    public Optional<DefnExpression> visitSexp(Sexp sexp) {
        if (!sexp.head().accept(IS_DEFN_SYMBOL)) {
            return Optional.empty();
        }
        if (sexp.size() != 4) {
            throw new IllegalArgumentException("Expecting 2 arguments but found " + (sexp.size() - 1));
        }
        Symbol name = AsSymbol.get(sexp.get(1));
        ParamBlock params = AsParamBlock.get(sexp.get(2));
        Exp body = sexp.get(3);
        return Optional.of(new DefnExpression(name, params, body));
    }

    @Override
    public Optional<DefnExpression> visitSymbol(Symbol symbol) {
        return Optional.empty();
    }

    @Override
    public Optional<DefnExpression> visitParamBlock(ParamBlock paramBlock) {
        return Optional.empty();
    }
}
