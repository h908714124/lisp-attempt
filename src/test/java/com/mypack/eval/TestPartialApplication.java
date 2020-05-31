package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.Test;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestPartialApplication {

    private final Environment env = new Environment();

    @Test
    void testPartialApplication() {
        Exp result = env.eval("((fn [f x] (f x)) (fn [n] n))", 1);
        assertTrue(eq("(fn [x] ((fn [n] n) x))", result));
    }
}
