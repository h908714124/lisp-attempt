package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.Test;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestFresh {

    private final Environment env = new Environment();

    @Test
    void testBadStuff() {
        Exp result = env.eval("((fn [n] (fn [f x] ((n (fn [g h] (h (g f))) (fn [u] x)) (fn [u] u)))) (fn [f x] (f (f x))))");
        assertTrue(eq("(fn [f x] (f x))", result));
    }
}
