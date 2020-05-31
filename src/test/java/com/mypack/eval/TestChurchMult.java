package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Test;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestChurchMult {

    @Test
    void testTwoTimesTwo() {
        Exp exp = LispParser.parse("((fn [m n] (fn [f x] (m (n f) x))) (fn [f x] (f (f x))) (fn [f x] (f (f x))))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(fn [f x] (f (f (f (f x)))))", result));
    }

    @Test
    void testTwoTimesThree() {
        Exp exp = LispParser.parse("((fn [m n] (fn [f x] (m (n f) x))) (fn [f x] (f (f x))) (fn [f x] (f (f (f x)))))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(fn [f x] (f (f (f (f (f (f x)))))))", result));
    }
}