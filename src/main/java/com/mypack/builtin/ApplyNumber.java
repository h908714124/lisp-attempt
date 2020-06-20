package com.mypack.builtin;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static com.mypack.builtin.Applicative.NUMBER_PATTERN;
import static com.mypack.builtin.HeadSplicing.assemble;

class ApplyNumber implements ExpVisitor<Optional<Exp>, List<? extends Exp>> {

    private final BigInteger m;

    ApplyNumber(BigInteger m) {
        this.m = m;
    }

    @Override
    public Optional<Exp> visitSexp(Sexp sexp, List<? extends Exp> tail) {
        if (tail.size() <= 1) {
            return Optional.empty();
        }
        Exp hewHead = nestedInvocations(m.intValue(), sexp, tail.get(1));
        return assemble(hewHead, tail.subList(2, tail.size()));
    }

    @Override
    public Optional<Exp> visitSymbol(Symbol symbol, List<? extends Exp> tail) {
        if (NUMBER_PATTERN.matcher(symbol.value()).matches()) {
            BigInteger n = new BigInteger(symbol.value());
            BigInteger r = n.pow(m.intValue());
            return assemble(Symbol.of(r), tail.subList(1, tail.size()));
        }
        if (tail.size() <= 1) {
            return Optional.empty();
        }
        Exp hewHead = nestedInvocations(m.intValue(), symbol, tail.get(1));
        return assemble(hewHead, tail.subList(2, tail.size()));
    }

    @Override
    public Optional<Exp> visitParamBlock(ParamBlock paramBlock) {
        return Optional.empty();
    }

    private static Exp nestedInvocations(int n, Exp f, Exp x) {
        Exp result = x;
        for (int i = 0; i < n; i++) {
            result = Sexp.create(f, result);
        }
        return result;
    }
}
