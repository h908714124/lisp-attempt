package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static com.mypack.test.TestUtil.assertEq;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(Lifecycle.PER_CLASS)
class TestIsZero {

    private final Environment env = new Environment();

    @Test
    void testCheckZero0() {
        Exp result = env.eval("((fn [n] (n (fn [x] (fn [a b] b)) (fn [a b] a))) (fn [f x] x))");
        assertEq(env, env.eval("(fn [a b] a)"), result);
    }

    @Test
    void testCheckZero1() {
        Exp result = env.eval("((fn [n] (n (fn [x] (fn [a b] b)) (fn [a b] a))) (fn [f x] (f x)))");
        assertEq(env, env.eval("(fn [a b] b)"), result);
    }

    @Test
    void testDef() {
        assertEquals("true", env.eval("(zero? 0)").toString());
        assertEquals("false", env.eval("(zero? 1)").toString());
        assertEquals("false", env.eval("(zero? 2)").toString());
    }
}