package com.mypack.parser;

import com.mypack.exp.EmptySexp;
import com.mypack.exp.Exp;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.exp.Value;

import java.math.BigInteger;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LispParser {

    public static Exp parse(String input) {
        return parse(tokens(input));
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
            if (value.matches("[-]?\\d+")) {
                return Value.of(new BigInteger(value));
            } else {
                return new Symbol(value);
            }
        }
        if (input.size() == 2) {
            return EmptySexp.instance();
        }
        List<List<Token>> segments = getSegments(input);
        List<Exp> tail = new ArrayList<>();
        for (int i = 1; i < segments.size(); i++) {
            List<Token> tokens = segments.get(i);
            tail.add(parse(tokens));
        }
        List<Token> head = segments.get(0);
        return new Sexp(parse(head), tail);
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
