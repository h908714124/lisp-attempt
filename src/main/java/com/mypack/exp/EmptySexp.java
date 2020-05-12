package com.mypack.exp;

public final class EmptySexp implements Exp {

    private static final EmptySexp INSTANCE = new EmptySexp();

    public static EmptySexp instance() {
        return INSTANCE;
    }

    private EmptySexp() {
    }

    @Override
    public <R> R accept(ExpVisitor<R> v) {
        return v.visitEmptySexp(this);
    }

    @Override
    public String toString() {
        return "()";
    }
}
