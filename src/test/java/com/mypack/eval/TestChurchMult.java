package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.Test;

import static com.mypack.test.TestUtil.assertEq;

class TestChurchMult {

    private final Environment env = new Environment();

    @Test
    void testTwoTimesTwo() {
        Exp result = env.eval("((fn [m n] (fn [f x] (m (n f) x))) (fn [f x] (f (f x))) (fn [f x] (f (f x))))");
        assertEq(env, env.eval("(fn [f x] (f (f (f (f x)))))"), result);
    }

    @Test
    void testTwoTimesThree() {
        Exp result = env.eval("((fn [m n] (fn [f x] (m (n f) x))) (fn [f x] (f (f x))) (fn [f x] (f (f (f x)))))");
        assertEq(env, env.eval("(fn [f x] (f (f (f (f (f (f x)))))))"), result);
    }
}