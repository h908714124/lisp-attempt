package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Test;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestIsZero {

    @Test
    void testCheckZero0() {
        Exp exp = LispParser.parse("((lambda (n) (n (lambda (x) (lambda (a b) b)) (lambda (a b) a))) (lambda (f x) x))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(lambda (a b) a)", result));
    }

    @Test
    void testCheckZero1() {
        Exp exp = LispParser.parse("((lambda (n) (n (lambda (x) (lambda (a b) b)) (lambda (a b) a))) (lambda (f x) (f x)))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(lambda (a b) b)", result));
    }
}