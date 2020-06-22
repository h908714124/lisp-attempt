package com.mypack.util;

import com.mypack.eval.LambdaExpression;
import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import java.math.BigInteger;
import java.util.Optional;

public class IsChurchNumeral implements ExpVisitor<Optional<BigInteger>, Integer> {

    private final Symbol f;
    private final Symbol x;

    private IsChurchNumeral(Symbol f, Symbol x) {
        this.f = f;
        this.x = x;
    }

    public static Optional<BigInteger> test(Exp exp) {
        Optional<LambdaExpression> lambda = IsLambdaExpression.test(exp);
        if (lambda.isEmpty()) {
            return Optional.empty();
        }
        ParamBlock symbols = lambda.get().symbols();
        if (symbols.size() != 2) {
            return Optional.empty();
        }
        IsChurchNumeral v = new IsChurchNumeral(symbols.head(), symbols.tail().get(0));
        return lambda.get().body().accept(v, 0);
    }

    @Override
    public Optional<BigInteger> visitSexp(Sexp sexp, Integer current) {
        if (sexp.size() != 2) {
            return Optional.empty();
        }
        if (!sexp.head().equals(f)) {
            return Optional.empty();
        }
        return sexp.tail().get(0).accept(this, current + 1);
    }

    @Override
    public Optional<BigInteger> visitSymbol(Symbol symbol, Integer current) {
        if (symbol.equals(x)) {
            return Optional.of(BigInteger.valueOf(current));
        }
        return Optional.empty();
    }

    @Override
    public Optional<BigInteger> visitParamBlock(ParamBlock paramBlock) {
        return Optional.empty();
    }
}
