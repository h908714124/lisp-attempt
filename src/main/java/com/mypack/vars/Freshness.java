package com.mypack.vars;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.AsSexp;
import com.mypack.util.AsSymbol;
import com.mypack.util.IsLambdaExpression;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Freshness implements ExpVisitor<Optional<Symbol>> {

    private final Set<Symbol> seen = new HashSet<>();

    private Freshness() {
    }

    public static Optional<Symbol> test(Exp exp) {
        return exp.accept(new Freshness());
    }

    @Override
    public Optional<Symbol> visitSexp(Sexp sexp) {
        if (IsLambdaExpression.test(sexp)) {
            Sexp symbols = AsSexp.get(sexp.tail().get(0));
            for (Exp symbol : symbols.asList()) {
                Symbol sym = AsSymbol.get(symbol);
                if (!seen.add(sym)) {
                    return Optional.of(sym);
                }
            }
            return sexp.tail().get(1).accept(this);
        }
        for (Exp exp : sexp.asList()) {
            Optional<Symbol> test = exp.accept(this);
            if (test.isPresent()) {
                return test;
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Symbol> visitSymbol(Symbol symbol) {
        return Optional.empty();
    }
}
