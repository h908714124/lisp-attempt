package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class LispParser {

    private static final Pattern NEWLINE_PATTERN = Pattern.compile("\\R");

    static Exp parse(String input) {
        return parseList(input).get(0);
    }

    static List<Exp> parseList(String input) {
        List<Exp> result = new ArrayList<>();
        for (List<Token> tokens : tokens(input)) {
            result.add(group(tokens));
        }
        return result;
    }

    static Exp group(List<Token> input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("no tokens in group");
        }
        if (!input.get(0).isOpeningParentheses() && !input.get(0).isOpeningBracket()) {
            if (input.size() != 1) {
                throw new IllegalArgumentException("group of more than one symbol");
            }
            String value = input.get(0).value();
            return Symbol.of(value);
        }
        if (input.size() == 2) {
            throw new IllegalArgumentException("empty sexp");
        }
        if (input.get(0).isOpeningBracket()) {
            return ParamBlock.create(getParamBlockContents(input.subList(1, input.size() - 1)));
        }
        List<List<Token>> segments = getSexpSegments(input);
        List<Exp> tail = new ArrayList<>();
        for (int i = 1; i < segments.size(); i++) {
            List<Token> tokens = segments.get(i);
            tail.add(group(tokens));
        }
        List<Token> head = segments.get(0);
        return Sexp.create(group(head), tail);
    }

    static List<Symbol> getParamBlockContents(List<Token> tokens) {
        List<Symbol> result = new ArrayList<>(tokens.size());
        for (Token token : tokens) {
            if (token.isBrace()) {
                throw new IllegalArgumentException("invalid param block");
            }
            result.add(Symbol.of(token.value()));
        }
        return result;
    }

    static List<List<Token>> getSexpSegments(List<Token> input) {
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
                Token t = next.getKey();
                exp.add(t);
                height += t.height();
                input = next.getValue();
            } while (height > 0);
            result.add(exp);
        }
        return result;
    }

    static Map.Entry<Token, String> readNextToken(String input) {
        input = input.trim();
        if (input.startsWith(";")) {
            Matcher matcher = NEWLINE_PATTERN.matcher(input);
            if (matcher.matches()) {
                int linebreak = matcher.end();
                input = input.substring(linebreak).trim();
            }
        }
        if (isBrace(input.charAt(0))) {
            return new AbstractMap.SimpleImmutableEntry<>(new Token(Character.toString(input.charAt(0))), input.substring(1));
        }
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (input.length() > i && !isWhitespace(input.charAt(i)) && !isBrace(input.charAt(i))) {
            result.append(input.charAt(i));
            i++;
        }
        return new AbstractMap.SimpleImmutableEntry<>(new Token(result.toString()), input.substring(i));
    }

    private static boolean isBrace(char c) {
        return c == '(' || c == ')' || c == '[' || c == ']';
    }

    private static boolean isWhitespace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }
}