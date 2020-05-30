package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Test;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestPredecessor {

    @Test
    void testPredecessorOfZero() {
        Exp exp = LispParser.parse("((lambda (n) (lambda (f x) ((n (lambda (g h) (h (g f))) (lambda (u) x)) (lambda (u1) u1)))) (lambda (f1 x1) x1))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(lambda (f x) x)", result));
    }

    @Test
    void testPredecessorOfOne() {
        Exp exp = LispParser.parse("((lambda (n) (lambda (f x) ((n (lambda (g h) (h (g f))) (lambda (u) x)) (lambda (u1) u1)))) (lambda (f1 x1) (f1 x1)))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(lambda (f x) x)", result));
    }

    @Test
    void testPredecessorOfTwo() {
        Exp exp = LispParser.parse("((lambda (n) (lambda (f x) ((n (lambda (g h) (h (g f))) (lambda (u) x)) (lambda (u1) u1)))) (lambda (f1 x1) (f1 (f1 x1))))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(lambda (f x) (f x))", result));
    }
}