package com.mypack.util;

import com.mypack.eval.LambdaExpression;
import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import java.util.Optional;

public class IsLambdaExpression implements ExpVisitor<Optional<LambdaExpression>> {

    private static final IsLambdaExpression INSTANCE = new IsLambdaExpression();

    private static final IsSpecificSymbolVisitor IS_LAMBDA = new IsSpecificSymbolVisitor(Symbol.fn());

    public static Optional<LambdaExpression> test(Exp exp) {
        return exp.accept(INSTANCE);
    }

    @Override
    public Optional<LambdaExpression> visitSexp(Sexp sexp) {
        if (!sexp.head().accept(IS_LAMBDA)) {
            return Optional.empty();
        }
        if (sexp.tail().size() != 2) {
            throw new IllegalArgumentException("expecting a lambda tail of length 2 but found " + sexp.tail());
        }
        ParamBlock paramBlock = AsParamBlock.get(sexp.tail().get(0));
        return Optional.of(new LambdaExpression(paramBlock, sexp.tail().get(1)));
    }

    @Override
    public Optional<LambdaExpression> visitSymbol(Symbol symbol) {
        return Optional.empty();
    }

    @Override
    public Optional<LambdaExpression> visitParamBlock(ParamBlock paramBlock) {
        return Optional.empty();
    }
}
