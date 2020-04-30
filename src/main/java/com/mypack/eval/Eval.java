package com.mypack.eval;

import com.mypack.exp.EmptySexp;
import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import java.math.BigInteger;
import java.util.List;

public class Eval implements ExpVisitor<BigInteger> {

    @Override
    public BigInteger visitEmptySexp(EmptySexp emptySexp) {
        return BigInteger.ZERO;
    }

    @Override
    public BigInteger visitSexp(Sexp sexp) {
        if (!(sexp.head() instanceof Symbol)) {
            throw new IllegalArgumentException("Not a symbol: " + sexp.head().getClass());
        }
        Symbol head = (Symbol) sexp.head();
        if ("+".equals(head.value())) {
            return evalPlus(sexp);
        } else if ("*".equals(head.value())) {
            return evalTimes(sexp);
        } else {
            throw new IllegalArgumentException("Unknown symbol: " + sexp.head());
        }
    }

    private BigInteger evalPlus(Sexp sexp) {
        List<Exp> tail = sexp.tail();
        BigInteger n = BigInteger.ZERO;
        for (Exp exp : tail) {
            n = n.add(exp.accept(this));
        }
        return n;
    }

    private BigInteger evalTimes(Sexp sexp) {
        List<Exp> tail = sexp.tail();
        BigInteger n = BigInteger.ONE;
        for (Exp exp : tail) {
            n = n.multiply(exp.accept(this));
        }
        return n;
    }

    @Override
    public BigInteger visitSymbol(Symbol symbol) {
        return new BigInteger(symbol.value());
    }
}
