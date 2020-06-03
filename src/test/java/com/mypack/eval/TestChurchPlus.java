package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.Test;

import static com.mypack.test.TestUtil.assertEq;

class TestChurchPlus {

    private final Environment env = new Environment();

    @Test
    void testZeroPlusZero() {
        Exp result = env.eval("((fn [m n] (fn [f x] (m f (n f x)))) (fn [f x] x) (fn [f x] x))");
        assertEq(env, env.eval("(fn [f x] x)"), result);
    }

    @Test
    void testZeroPlusOne() {
        Exp result = env.eval("((fn [m n] (fn [f x] (m f (n f x)))) (fn [f x] x) (fn [f x] (f x)))");
        assertEq(env, env.eval("(fn [f x] (f x))"), result);
    }

    @Test
    void testOnePlusZero() {
        Exp result = env.eval("((fn [m n] (fn [f x] (m f (n f x)))) (fn [f x] (f x)) (fn [f x] x))");
        assertEq(env, env.eval("(fn [f x] (f x))"), result);
    }

    @Test
    void testOnePlusOne() {
        Exp result = env.eval("((fn [m n] (fn [f x] (m f (n f x)))) (fn [f x] (f x)) (fn [f x] (f x)))");
        assertEq(env, env.eval("(fn [f x] (f (f x)))"), result);
    }
}