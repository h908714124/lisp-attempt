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
        return '(' == value.charAt(0);
    }

    public boolean isOpeningBracket() {
        return '[' == value.charAt(0);
    }

    public boolean isClosingParentheses() {
        return ')' == value.charAt(0);
    }

    public boolean isClosingBracket() {
        return ']' == value.charAt(0);
    }

    public int height() {
        return pheight() + bheight();
    }

    private int pheight() {
        if ('(' == value.charAt(0)) {
            return 1;
        }
        if (')' == value.charAt(0)) {
            return -1;
        }
        return 0;
    }

    private int bheight() {
        if ('[' == value.charAt(0)) {
            return 1;
        }
        if (']' == value.charAt(0)) {
            return -1;
        }
        return 0;
    }

    public boolean isBrace() {
        return isOpeningParentheses() || isClosingParentheses() || isOpeningBracket() || isClosingBracket();
    }

    @Override
    public String toString() {
        return value;
    }
}
