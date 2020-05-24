package com.mypack.eval;

import com.mypack.exp.EmptySexp;
import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.exp.Value;
import com.mypack.util.AsSymbol;
import com.mypack.util.AsValue;
import com.mypack.util.IsValue;

import java.math.BigInteger;
import java.util.List;
import java.util.function.Function;

public class ExtractFunction implements ExpVisitor<Function<List<Exp>, Exp>> {

    @Override
    public Function<List<Exp>, Exp> visitEmptySexp(EmptySexp emptySexp) {
        return args -> emptySexp;
    }

    @Override
    public Function<List<Exp>, Exp> visitSexp(Sexp sexp) {
        Symbol head = AsSymbol.get(sexp.head());
        if (!head.value().equals("lambda")) {
            throw new IllegalArgumentException("lambda expected but found " + head);
        }
        return Lambda.createLambda(sexp.tail());
    }

    @Override
    public Function<List<Exp>, Exp> visitValue(Value value) {
        return args -> value;
    }

    @Override
    public Function<List<Exp>, Exp> visitSymbol(Symbol symbol) {
        if ("+".equals(symbol.value())) {
            return this::evalPlus;
        } else if ("*".equals(symbol.value())) {
            return this::evalTimes;
        } else if ("zero?".equals(symbol.value())) {
            return this::isZero;
        }
        throw new IllegalArgumentException("Unknown symbol: " + symbol);
    }

    private Exp isZero(List<Exp> tail) {
        if (tail.size() != 3) {
            throw new IllegalArgumentException();
        }
        Exp tail0 = Eval.iterEval(tail.get(0));
        if (tail0 instanceof Value) {
            Value result = AsValue.get(tail0);
            if (result.value().equals(BigInteger.ZERO)) {
                return tail.get(1);
            } else {
                return tail.get(2);
            }
        } else {
            return tail.get(2);
        }
    }

    private Exp evalPlus(List<Exp> tail) {
        if (!tail.stream().allMatch(IsValue::test)) {
            return new Sexp(Symbol.of("+"), tail);
        }
        BigInteger n = BigInteger.ZERO;
        for (Exp exp : tail) {
            n = n.add(AsValue.get(exp.accept(new Eval())).value());
        }
        return Value.of(n);
    }

    private Exp evalTimes(List<Exp> tail) {
        if (!tail.stream().allMatch(IsValue::test)) {
            return new Sexp(Symbol.of("*"), tail);
        }
        BigInteger n = BigInteger.ONE;
        for (Exp exp : tail) {
            n = n.multiply(AsValue.get(exp.accept(new Eval())).value());
        }
        return Value.of(n);
    }
}
