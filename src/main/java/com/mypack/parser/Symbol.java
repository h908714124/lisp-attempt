package com.mypack.parser;

public class Symbol implements Exp {

    private final String value;

    public Symbol(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
