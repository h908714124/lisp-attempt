package com.mypack.exp;

public interface Exp {

    <R> R accept(ExpVisitor<R> v);
}
