package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Test;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestChurchPlus {

    @Test
    void testZeroPlusZero() {
        Exp exp = LispParser.parse("((fn [m n] (fn [f x] (m f (n f x)))) (fn [f x] x) (fn [f x] x))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(fn [f x] x)", result));
    }

    @Test
    void testZeroPlusOne() {
        Exp exp = LispParser.parse("((fn [m n] (fn [f x] (m f (n f x)))) (fn [f x] x) (fn [f x] (f x)))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(fn [f x] (f x))", result));
    }

    @Test
    void testOnePlusZero() {
        Exp exp = LispParser.parse("((fn [m n] (fn [f x] (m f (n f x)))) (fn [f x] (f x)) (fn [f x] x))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(fn [f x] (f x))", result));
    }

    @Test
    void testOnePlusOne() {
        Exp exp = LispParser.parse("((fn [m n] (fn [f x] (m f (n f x)))) (fn [f x] (f x)) (fn [f x] (f x)))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(fn [f x] (f (f x)))", result));
    }
}