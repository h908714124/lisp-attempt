package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestChurchPlus {

    @Test
    void testZeroPlusZero() {
        Exp exp = LispParser.parse("((lambda (m n) (lambda (f x) (m f (n f x)))) (lambda (f x) x) (lambda (f x) x))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(lambda (f x) x)", result));
    }

    @Test
    void testZeroPlusOne() {
        Exp exp = LispParser.parse("((lambda (m n) (lambda (f x) (m f (n f x)))) (lambda (f x) x) (lambda (f x) (f x)))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(lambda (f x) (f x))", result));
    }

    @Test
    void testOnePlusZero() {
        Exp exp = LispParser.parse("((lambda (m n) (lambda (f x) (m f (n f x)))) (lambda (f x) (f x)) (lambda (f x) x))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(lambda (f x) (f x))", result));
    }

    @Test
    void testOnePlusOne() {
        Exp exp = LispParser.parse("((lambda (m n) (lambda (f x) (m f (n f x)))) (lambda (f x) (f x)) (lambda (f x) (f x)))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(lambda (f x) (f (f x)))", result));
    }
}