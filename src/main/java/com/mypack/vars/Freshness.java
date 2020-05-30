package com.mypack.vars;

import com.mypack.eval.LambdaExpression;
import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.AsSymbol;
import com.mypack.util.IsLambdaExpression;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

// Useful for detecting bugs that lead to non-fresh expressions.
// A symbol is fresh in an expression if it's not reused in a parameter list.
// An expression is fresh if all its symbols are fresh.
// For example, the following expression is not fresh, because a is reused:
// (lambda (f a) (f (lambda a) (a a)))
// Evaluating a fresh expression must always result in another fresh expression.
public class Freshness implements ExpVisitor<Optional<Symbol>> {

    private final Set<Symbol> seen;

    private Freshness(Set<Symbol> seen) {
        this.seen = seen;
    }

    // return empty if exp is fresh, otherwise return a non-fresh symbol
    public static Optional<Symbol> test(Exp exp) {
        return exp.accept(new Freshness(Collections.emptySet()));
    }

    @Override
    public Optional<Symbol> visitSexp(Sexp sexp) {
        Optional<LambdaExpression> lambda = IsLambdaExpression.test(sexp);
        if (lambda.isPresent()) {
            List<Symbol> symbols = lambda.get().symbols();
            Set<Symbol> newSeen = new HashSet<>(seen);
            for (Exp symbol : symbols) {
                Symbol sym = AsSymbol.get(symbol);
                if (!newSeen.add(sym)) {
                    return Optional.of(sym);
                }
            }
            return sexp.tail().get(1).accept(new Freshness(newSeen));
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
