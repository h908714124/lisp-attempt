package com.mypack.parser;

import com.mypack.exp.Exp;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class LispParserTest {

    @Test
    void testParse() {
        Exp exp = LispParser.parse("(car (cdr))");
        Assertions.assertTrue(exp instanceof Sexp);
        Assertions.assertTrue(((Sexp) exp).head() instanceof Symbol);
        Assertions.assertEquals("car", ((Symbol) ((Sexp) exp).head()).value());
        Assertions.assertEquals(1, ((Sexp) exp).tail().size());
        Assertions.assertTrue(((Sexp) exp).tail().get(0) instanceof Sexp);
        Assertions.assertTrue(((Sexp) ((Sexp) exp).tail().get(0)).head() instanceof Symbol);
        Assertions.assertEquals("cdr", ((Symbol) ((Sexp) ((Sexp) exp).tail().get(0)).head()).value());
        Assertions.assertTrue(((Sexp) ((Sexp) exp).tail().get(0)).tail().isEmpty());
    }

    @Test
    void testSegments() {
        List<List<Token>> structure = LispParser.getSegments(LispParser.tokens("(car (cdr))"));
        Assertions.assertEquals(2, structure.size());
    }

    @Test
    void testStructureEmpty() {
        List<List<Token>> structure = LispParser.getSegments(LispParser.tokens("()"));
        Assertions.assertTrue(structure.isEmpty());
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

    @Test
    void testTokensEmpty() {
        List<Token> tokens = LispParser.tokens("()");
        Assertions.assertEquals(2, tokens.size());
        Assertions.assertEquals("(", tokens.get(0).value());
        Assertions.assertEquals(")", tokens.get(1).value());
    }
}