package com.mypack.util;

import com.mypack.exp.Symbol;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class SetUtil {

    private SetUtil() {
    }

    public static Set<Symbol> union(Collection<Symbol> a, Collection<Symbol> b) {
        Set<Symbol> result = new LinkedHashSet<>(a.size() + b.size());
        result.addAll(a);
        result.addAll(b);
        return result;
    }

    public static Set<Symbol> intersection(Set<Symbol> a, Set<Symbol> b) {
        Set<Symbol> result = new LinkedHashSet<>();
        for (Symbol as : a) {
            if (b.contains(as)) {
                result.add(as);
            }
        }
        return result;
    }
}
