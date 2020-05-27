package com.mypack.vars;

import com.mypack.exp.Symbol;

import java.util.Set;

public class AnalysisResult {

    private final Set<Symbol> bound;

    private final Set<Symbol> unbound;

    AnalysisResult(Set<Symbol> bound, Set<Symbol> unbound) {
        this.bound = Set.copyOf(bound);
        this.unbound = Set.copyOf(unbound);
    }

    public Set<Symbol> getBound() {
        return bound;
    }

    public Set<Symbol> getUnbound() {
        return unbound;
    }
}
