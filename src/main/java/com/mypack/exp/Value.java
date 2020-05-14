package com.mypack.exp;

import java.math.BigInteger;

public final class Value implements Exp {

    private final BigInteger value;

    private Value(BigInteger value) {
        this.value = value;
    }

    public static Value of(BigInteger value) {
        return new Value(value);
    }

    @Override
    public <R> R accept(ExpVisitor<R> v) {
        return v.visitValue(this);
    }

    public BigInteger value() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
