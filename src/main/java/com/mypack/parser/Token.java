package com.mypack.parser;

public class Token {

    private final String value;

    Token(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public boolean isOpeningParentheses() {
        return "(".equals(value);
    }

    public boolean isClosingParentheses() {
        return ")".equals(value);
    }
}
