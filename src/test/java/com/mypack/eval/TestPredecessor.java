package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Test;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestPredecessor {

    @Test
    void testPredecessorOfZero() {
        Exp exp = LispParser.parse("((fn [n] (fn [f x] ((n (fn [g h] (h (g f))) (fn [u] x)) (fn [u1] u1)))) (fn [f1 x1] x1))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(fn [f x] x)", result));
    }

    @Test
    void testPredecessorOfOne() {
        Exp exp = LispParser.parse("((fn [n] (fn [f x] ((n (fn [g h] (h (g f))) (fn [u] x)) (fn [u1] u1)))) (fn [f1 x1] (f1 x1)))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(fn [f x] x)", result));
    }

    @Test
    void testPredecessorOfTwo() {
        Exp exp = LispParser.parse("((fn [n] (fn [f x] ((n (fn [g h] (h (g f))) (fn [u] x)) (fn [u1] u1)))) (fn [f1 x1] (f1 (f1 x1))))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(fn [f x] (f x))", result));
    }
}