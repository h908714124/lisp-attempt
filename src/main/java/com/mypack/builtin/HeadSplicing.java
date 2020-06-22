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

public class HeadSplicing implements ExpVisitor<Optional<Exp>, List<? extends Exp>> {

    private HeadSplicing() {
    }

    private static final HeadSplicing INSTANCE = new HeadSplicing();

    public static Optional<Exp> assemble(Exp head, List<? extends Exp> tail, int cut) {
        tail = tail.subList(cut, tail.size());
        if (tail.isEmpty()) {
            return Optional.of(head);
        }
        if (IsLambdaExpression.test(head).isPresent()) {
            return Optional.empty();
        }
        return head.accept(INSTANCE, tail);
    }

    public static Optional<Exp> simplify(Sexp sexp) {
        if (sexp.tail().isEmpty()) {
            return Optional.empty();
        }
        if (!IsSexp.test(sexp.head())) {
            return Optional.empty();
        }
        return assemble(sexp.head(), sexp.tail(), 0);
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
        return Optional.of(Sexp.create(newHead, tail));
    }

    @Override
    public Optional<Exp> visitParamBlock(ParamBlock paramBlock) {
        return Optional.empty();
    }
}
