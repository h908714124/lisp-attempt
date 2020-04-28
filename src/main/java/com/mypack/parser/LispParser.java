package com.mypack.parser;

public class LispParser {

    public Exp parse(String input) {
        if (input.isEmpty()) {
            return null;
        }
        if (!input.startsWith("(")) {
            return new Symbol(input);
        }
        if (input.length() == 2) {
            return EmptySexp.instance();
        }
    }
}
