package com.mypack.exp;

public class Symbol implements Exp {

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
}
