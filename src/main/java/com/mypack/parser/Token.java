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

    public int height() {
        if (isOpeningParentheses()) {
            return 1;
        }
        if (isClosingParentheses()) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return value;
    }
}
