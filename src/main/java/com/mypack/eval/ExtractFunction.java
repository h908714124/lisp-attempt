package com.mypack.eval;

import com.mypack.exp.EmptySexp;
import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.exp.Value;
import com.mypack.util.AsSymbol;
import com.mypack.util.AsValue;

import java.math.BigInteger;
import java.util.List;
import java.util.function.Function;

public class ExtractFunction implements ExpVisitor<Function<List<Exp>, Exp>> {

    @Override
    public Function<List<Exp>, Exp> visitEmptySexp(EmptySexp emptySexp) {
        throw new IllegalArgumentException();
    }

    @Override
    public Function<List<Exp>, Exp> visitSexp(Sexp sexp) {
        Symbol head = AsSymbol.get(sexp.head());
        if (!head.value().equals("lambda")) {
            throw new IllegalArgumentException("lambda expected");
        }
        return Lambda.createLambda(sexp.tail());
    }

    @Override
    public Function<List<Exp>, Exp> visitValue(Value value) {
        throw new IllegalArgumentException("can't use value " + value +
                " as function");
    }

    @Override
    public Function<List<Exp>, Exp> visitSymbol(Symbol symbol) {
        if ("+".equals(symbol.value())) {
            return this::evalPlus;
        } else if ("*".equals(symbol.value())) {
            return this::evalTimes;
        }
        throw new IllegalArgumentException("Unknown symbol: " + symbol);
    }

    private Exp evalPlus(List<Exp> tail) {
        BigInteger n = BigInteger.ZERO;
        for (Exp exp : tail) {
            n = n.add(AsValue.get(exp.accept(new ExtractNumber())).value());
        }
        return Value.of(n);
    }

    private Exp evalTimes(List<Exp> tail) {
        BigInteger n = BigInteger.ONE;
        for (Exp exp : tail) {
            n = n.multiply(AsValue.get(exp.accept(new ExtractNumber())).value());
        }
        return Value.of(n);
    }
}
