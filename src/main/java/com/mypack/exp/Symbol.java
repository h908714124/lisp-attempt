package com.mypack.exp;

import java.util.HashMap;
import java.util.Map;

public final class Symbol implements Exp {

    private final String value;

    private Symbol(String value) {
        this.value = value;
    }

    private static final Map<String, Symbol> SYMBOLS = new HashMap<>();

    public static Symbol of(String value) {
        return SYMBOLS.computeIfAbsent(value, Symbol::new);
    }

    public static Symbol lambda() {
        return of("lambda");
    }

    public String value() {
        return value;
    }

    @Override
    public <R> R accept(ExpVisitor<R> v) {
        return v.visitSymbol(this);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Symbol symbol = (Symbol) o;
        return value.equals(symbol.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
