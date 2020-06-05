package com.mypack.vars;

import com.mypack.exp.Symbol;
import com.mypack.util.SetUtil;

import java.util.Set;

public class AnalysisResult {

    private final Set<Symbol> bound;

    private final Set<Symbol> all;

    AnalysisResult(Set<Symbol> bound, Set<Symbol> unbound) {
        this.bound = Set.copyOf(bound);
        this.all = SetUtil.union(bound, unbound);
    }

    public Set<Symbol> bound() {
        return bound;
    }

    public Set<Symbol> all() {
        return all;
    }
}
