package com.mypack.exp;

public final class Symbol implements Exp {

    public static Symbol lambda() {
        return new Symbol("lambda");
    }

    private final String value;

    public Symbol(String value) {
        this.value = value;
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
