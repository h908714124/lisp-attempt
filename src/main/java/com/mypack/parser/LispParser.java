package com.mypack.parser;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LispParser {

    static Exp parse(List<Token> input) {
        if (input.isEmpty()) {
            return null;
        }
        Deque<Token> tokens = new LinkedList<>(input);
        Token token = tokens.pollFirst();
        return null;
    }

    static List<Token> tokens(String input) {
        List<Token> result = new ArrayList<>();
        while (!input.isEmpty()) {
            Map.Entry<Token, String> next = readNextToken(input);
            result.add(next.getKey());
            input = next.getValue();
        }
        return result;
    }

    static Map.Entry<Token, String> readNextToken(String input) {
        input = input.trim();
        if (input.startsWith("(")) {
            return new AbstractMap.SimpleImmutableEntry<>(new Token("("), input.substring(1));
        }
        if (input.startsWith(")")) {
            return new AbstractMap.SimpleImmutableEntry<>(new Token(")"), input.substring(1));
        }
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (input.charAt(i) != ' ' && input.charAt(i) != '(' && input.charAt(i) != ')') {
            result.append(input.charAt(i));
            i++;
        }
        return new AbstractMap.SimpleImmutableEntry<>(new Token(result.toString()), input.substring(i));
    }
}
