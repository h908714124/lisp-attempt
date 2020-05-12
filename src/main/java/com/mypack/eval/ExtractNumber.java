package com.mypack.eval;

import com.mypack.exp.EmptySexp;
import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import java.math.BigInteger;
import java.util.List;
import java.util.function.Function;

public class ExtractNumber implements ExpVisitor<BigInteger> {

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
        Function<List<Exp>, BigInteger> function = head.accept(new ExtractFunction());
        return function.apply(sexp.tail());
    }

    @Override
    public BigInteger visitSymbol(Symbol symbol) {
        return new BigInteger(symbol.value());
    }
}
