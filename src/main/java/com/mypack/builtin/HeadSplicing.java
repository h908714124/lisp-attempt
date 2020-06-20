package com.mypack.builtin;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.IsLambdaExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HeadSplicing implements ExpVisitor<Optional<Exp>, List<? extends Exp>> {

    private HeadSplicing() {
    }

    private static final HeadSplicing INSTANCE = new HeadSplicing();

    public static Optional<Exp> trySplicing(Exp head, List<? extends Exp> tail) {
        if (tail.isEmpty() || IsLambdaExpression.test(head).isPresent()) {
            return Optional.empty();
        }
        return head.accept(INSTANCE, tail);
    }

    public static Optional<Exp> trySplicing(Sexp sexp) {
        return trySplicing(sexp.head(), sexp.tail());
    }

    @Override
    public Optional<Exp> visitSexp(Sexp newHead, List<? extends Exp> tail) {
        List<Exp> result = new ArrayList<>(newHead.size() + tail.size());
        result.addAll(newHead.asList());
        result.addAll(tail);
        return Optional.of(Sexp.create(result));
    }

    @Override
    public Optional<Exp> visitSymbol(Symbol newHead, List<? extends Exp> tail) {
        return Optional.empty();
    }

    @Override
    public Optional<Exp> visitParamBlock(ParamBlock paramBlock) {
        return Optional.empty();
    }
}
