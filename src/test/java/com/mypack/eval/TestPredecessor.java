package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestPredecessor {

    private static final Environment ENV = new Environment();

    @BeforeAll
    static void setUp() {
        ENV.load(Paths.get("src/lisp/fact.clj"));
    }

    @Test
    void testPredecessorOfZero() {
        Exp result = ENV.eval("(pred 0)");
        assertTrue(eq(ENV.eval("0"), result));
    }

    @Test
    void testPredecessorOfOne() {
        Exp result = ENV.eval("(pred 1)");
        assertTrue(eq(ENV.eval("0"), result));
    }

    @Test
    void testPredecessorOfTwo() {
        Exp result = ENV.eval("(pred 2)");
        assertTrue(eq(ENV.eval("1"), result));
    }

    @Test
    void testK() {
        Exp result = ENV.eval("((K 2) 1)");
        assertTrue(eq(ENV.eval("2"), result));
    }
}