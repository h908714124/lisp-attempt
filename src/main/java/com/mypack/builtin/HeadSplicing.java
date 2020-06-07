package com.mypack.builtin;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.IsLambdaExpression;
import com.mypack.util.IsSexp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HeadSplicing implements ExpVisitor<Sexp, Sexp> {

    private HeadSplicing() {
    }

    private static final HeadSplicing INSTANCE = new HeadSplicing();

    public static Optional<Exp> trySplicing(Sexp sexp) {
        if (sexp.size() >= 2
                && IsSexp.test(sexp.head())
                && IsLambdaExpression.test(sexp.head()).isEmpty()) {
            return Optional.of(sexp.head().accept(INSTANCE, sexp));
        }
        return Optional.empty();
    }

    @Override
    public Sexp visitSexp(Sexp head, Sexp outer) {
        List<Exp> result = new ArrayList<>(head.size() + outer.size() - 1);
        result.addAll(head.asList());
        result.addAll(outer.tail());
        return Sexp.create(result);
    }

    @Override
    public Sexp visitSymbol(Symbol head, Sexp outer) {
        return Sexp.create(head, outer.subList(1));
    }

    @Override
    public Sexp visitParamBlock(ParamBlock paramBlock) {
        throw new IllegalArgumentException();
    }
}
