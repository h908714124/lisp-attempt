package com.mypack.parser;

import com.mypack.exp.Exp;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LispParser {

    public static Exp parse(String input) {
        return parseList(input).get(0);
    }

    public static List<Exp> parseList(String input) {
        List<Exp> result = new ArrayList<>();
        for (List<Token> tokens : tokens(input)) {
            result.add(parse(tokens));
        }
        return result;
    }

    static Exp parse(List<Token> input) {
        if (input.isEmpty()) {
            return null;
        }
        if (!input.get(0).isOpeningParentheses()) {
            if (input.size() != 1) {
                throw new IllegalArgumentException();
            }
            String value = input.get(0).value();
            return Symbol.of(value);
        }
        if (input.size() == 2) {
            throw new IllegalArgumentException("Empty");
        }
        List<List<Token>> segments = getSegments(input);
        List<Exp> tail = new ArrayList<>();
        for (int i = 1; i < segments.size(); i++) {
            List<Token> tokens = segments.get(i);
            tail.add(parse(tokens));
        }
        List<Token> head = segments.get(0);
        return Sexp.create(parse(head), tail);
    }

    static List<List<Token>> getSegments(List<Token> input) {
        Deque<Token> tokens = new LinkedList<>(input);
        tokens.removeFirst();
        tokens.removeLast();
        List<List<Token>> result = new ArrayList<>();
        while (!tokens.isEmpty()) {
            List<Token> t = new ArrayList<>();
            int height = 0;
            do {
                Token token = tokens.removeFirst();
                height += token.height();
                t.add(token);
            } while (height >= 1);
            result.add(t);
        }
        return result;
    }

    static List<List<Token>> tokens(String input) {
        List<List<Token>> result = new ArrayList<>();
        while (!input.isEmpty()) {
            List<Token> exp = new ArrayList<>();
            int height = 0;
            do {
                Map.Entry<Token, String> next = readNextToken(input);
                exp.add(next.getKey());
                input = next.getValue();
                height += next.getKey().height();
            } while (height > 0);
            result.add(exp);
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
        while (input.length() > i && !isWhitespace(input.charAt(i)) && input.charAt(i) != '(' && input.charAt(i) != ')') {
            result.append(input.charAt(i));
            i++;
        }
        return new AbstractMap.SimpleImmutableEntry<>(new Token(result.toString()), input.substring(i));
    }

    private static boolean isWhitespace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }
}
