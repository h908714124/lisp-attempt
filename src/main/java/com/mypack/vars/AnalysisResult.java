package com.mypack.vars;

import com.mypack.eval.LambdaExpression;
import com.mypack.exp.Symbol;

import java.util.Set;

public class AnalysisResult {

    private final Set<Symbol> bound;

    private final Set<Symbol> unbound;

    private final Set<Symbol> all;

    AnalysisResult(Set<Symbol> bound, Set<Symbol> unbound) {
        this.bound = Set.copyOf(bound);
        this.unbound = Set.copyOf(unbound);
        this.all = LambdaExpression.union(bound, unbound);
    }

    public Set<Symbol> bound() {
        return bound;
    }

    public Set<Symbol> unbound() {
        return unbound;
    }

    public Set<Symbol> all() {
        return all;
    }
}
