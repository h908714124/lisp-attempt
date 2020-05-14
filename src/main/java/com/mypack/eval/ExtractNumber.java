package com.mypack.eval;

import com.mypack.exp.EmptySexp;
import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.exp.Value;

import java.math.BigInteger;
import java.util.List;
import java.util.function.Function;

public class ExtractNumber implements ExpVisitor<Exp> {

    @Override
    public Exp visitEmptySexp(EmptySexp emptySexp) {
        return Value.of(BigInteger.ZERO);
    }

    @Override
    public Exp visitSexp(Sexp sexp) {
        if (!(sexp.head() instanceof Symbol)) {
            throw new IllegalArgumentException("Not a symbol: " + sexp.head().getClass());
        }
        Symbol head = (Symbol) sexp.head();
        Function<List<Exp>, Exp> function = head.accept(new ExtractFunction());
        return function.apply(sexp.tail());
    }

    @Override
    public Exp visitValue(Value value) {
        return value;
    }

    @Override
    public Exp visitSymbol(Symbol symbol) {
        return symbol;
    }


}
