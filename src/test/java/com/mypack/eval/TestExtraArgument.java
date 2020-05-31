package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.Test;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestExtraArgument {

    @Test
    void testExtraArgument() {
        Exp exp = new Environment().eval("((fn [x] (fn [n] n)) (fn [a b] a) (fn [a b] b))");
        assertTrue(eq("(fn [a b] b)", exp));
    }
}
