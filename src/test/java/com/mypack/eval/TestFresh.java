package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static com.mypack.test.TestUtil.assertEq;

@TestInstance(Lifecycle.PER_CLASS)
class TestFresh {

    private final Environment env = new Environment();

    @Test
    void testBadStuff() {
        Exp result = env.eval("((fn [n] (fn [f x] ((n (fn [g h] (h (g f))) (fn [u] x)) (fn [u] u)))) (fn [f x] (f (f x))))");
        assertEq(env, env.eval("(fn [f x] (f x))"), result);
    }
}
