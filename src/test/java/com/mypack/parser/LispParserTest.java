package com.mypack.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class LispParserTest {

    @Test
    void testParse() {
        Exp exp = LispParser.parse(LispParser.tokens("(car (cdr))"));
    }

    @Test
    void testTokens() {
        List<Token> tokens = LispParser.tokens("(car (cdr))");
        Assertions.assertEquals(6, tokens.size());
        Assertions.assertEquals("(", tokens.get(0).value());
        Assertions.assertEquals("car", tokens.get(1).value());
        Assertions.assertEquals("(", tokens.get(2).value());
        Assertions.assertEquals("cdr", tokens.get(3).value());
        Assertions.assertEquals(")", tokens.get(4).value());
        Assertions.assertEquals(")", tokens.get(5).value());
    }
}