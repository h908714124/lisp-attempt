package com.mypack.exp;

public interface Exp {

    <R, P> R accept(ExpVisitor<R, P> v, P p);
}
