package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Test;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestFresh {

    @Test
    void testBadStuff() {
        Exp exp = LispParser.parse("((fn [n] (fn [f x] ((n (fn [g h] (h (g f))) (fn [u] x)) (fn [u] u)))) (fn [f x] (f (f x))))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(fn [f x] (f x))", result));
    }
}
