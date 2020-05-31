package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestIsZero {

    private static final Environment ENV = new Environment();

    @BeforeAll
    static void setUp() {
        ENV.load(Paths.get("src/lisp/fact.clj"));
    }

    @Test
    void testCheckZero0() {
        Exp result = ENV.eval("((fn [n] (n (fn [x] (fn [a b] b)) (fn [a b] a))) (fn [f x] x))");
        assertTrue(eq("(fn [a b] a)", result));
    }

    @Test
    void testCheckZero1() {
        Exp result = ENV.eval("((fn [n] (n (fn [x] (fn [a b] b)) (fn [a b] a))) (fn [f x] (f x)))");
        assertTrue(eq("(fn [a b] b)", result));
    }

    @Test
    void testDef() {
        assertTrue(eq(ENV.eval("true"), ENV.eval("(zero? 0)")));
        assertTrue(eq(ENV.eval("false"), ENV.eval("(zero? 1)")));
        assertTrue(eq(ENV.eval("false"), ENV.eval("(zero? 2)")));
    }
}