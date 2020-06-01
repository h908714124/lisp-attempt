package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.Test;

import static com.mypack.test.AlphaEquivalence.assertEq;

class TestPartialApplication {

    private final Environment env = new Environment();

    @Test
    void testPartialApplication() {
        Exp result = env.eval("((fn [f x] (f x)) (fn [n] n))", 1);
        assertEq(env, env.eval("(fn [x] ((fn [n] n) x))", 0), result);
    }
}
