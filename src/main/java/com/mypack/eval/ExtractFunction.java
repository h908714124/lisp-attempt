package com.mypack.eval;

import com.mypack.exp.EmptySexp;
import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import java.math.BigInteger;
import java.util.List;
import java.util.function.Function;

public class ExtractFunction implements ExpVisitor<Function<List<Exp>, BigInteger>> {

    @Override
    public Function<List<Exp>, BigInteger> visitEmptySexp(EmptySexp emptySexp) {
        throw new IllegalArgumentException();
    }

    @Override
    public Function<List<Exp>, BigInteger> visitSexp(Sexp sexp) {
        throw new IllegalArgumentException();
    }

    @Override
    public Function<List<Exp>, BigInteger> visitSymbol(Symbol symbol) {
        if ("+".equals(symbol.value())) {
            return this::evalPlus;
        } else if ("*".equals(symbol.value())) {
            return this::evalTimes;
        }
        throw new IllegalArgumentException("Unknown symbol: " + symbol);
    }

    private BigInteger evalPlus(List<Exp> tail) {
        BigInteger n = BigInteger.ZERO;
        for (Exp exp : tail) {
            n = n.add(exp.accept(new ExtractNumber()));
        }
        return n;
    }

    private BigInteger evalTimes(List<Exp> tail) {
        BigInteger n = BigInteger.ONE;
        for (Exp exp : tail) {
            n = n.multiply(exp.accept(new ExtractNumber()));
        }
        return n;
    }
}
