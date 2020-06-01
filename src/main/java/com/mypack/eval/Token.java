package com.mypack.eval;

class Token {

    private final String value;

    Token(String value) {
        this.value = value;
    }

    String value() {
        return value;
    }

    boolean isOpeningParentheses() {
        return '(' == value.charAt(0);
    }

    boolean isOpeningBracket() {
        return '[' == value.charAt(0);
    }

    boolean isClosingParentheses() {
        return ')' == value.charAt(0);
    }

    boolean isClosingBracket() {
        return ']' == value.charAt(0);
    }

    int height() {
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

    boolean isBrace() {
        return isOpeningParentheses() || isClosingParentheses() || isOpeningBracket() || isClosingBracket();
    }

    @Override
    public String toString() {
        return value;
    }
}
