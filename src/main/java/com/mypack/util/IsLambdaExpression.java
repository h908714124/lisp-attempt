package com.mypack.util;

import com.mypack.eval.LambdaExpression;
import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class IsLambdaExpression implements ExpVisitor<Optional<LambdaExpression>> {

    private static final IsLambdaExpression INSTANCE = new IsLambdaExpression();

    public static Optional<LambdaExpression> test(Exp exp) {
        return exp.accept(INSTANCE);
    }

    @Override
    public Optional<LambdaExpression> visitSexp(Sexp sexp) {
        if (!IsLambdaSymbol.test(sexp.head())) {
            return Optional.empty();
        }
        if (sexp.tail().size() != 2) {
            throw new IllegalArgumentException("expecting a lambda tail of length 2 but found " + sexp.tail());
        }
        List<Symbol> symbols = AsSexp.get(sexp.tail().get(0)).asList().stream().map(AsSymbol::get).collect(Collectors.toList());
        return Optional.of(new LambdaExpression(symbols, sexp.tail().get(1)));
    }

    @Override
    public Optional<LambdaExpression> visitSymbol(Symbol symbol) {
        return Optional.empty();
    }
}
