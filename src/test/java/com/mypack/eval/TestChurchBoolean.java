package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static com.mypack.test.TestUtil.assertEq;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(Lifecycle.PER_CLASS)
class TestChurchBoolean {

    private final Environment env = new Environment();

    @Test
    void testChurchTrue() {
        Exp result = env.eval("((fn [a b] a) (fn [a b] a) (fn [a b] b))");
        assertEq(env, env.eval("(fn [a b] a)"), result);
    }

    @Test
    void testChurchFalse() {
        Exp result = env.eval("((fn [a b] b) (fn [a b] a) (fn [a b] b))");
        assertEq(env, env.eval("(fn [a b] b)"), result);
    }

    @Test
    void testFPChurchTrue1() {
        Exp result = env.eval("((fn [f] ((fn [x] (f x x)) (fn [x] (f x x)))) (fn [a b] a))");
        assertEq(env, env.eval("(fn [x] x)"), result);
    }

    @Test
    void testFPChurchTrue4() {
        Exp result = env.eval("(fn [x] ((fn [a b] a) x x))");
        assertEq(env, env.eval("(fn [x] x)"), result);
    }

    @Test
    void testTrueBuiltin() {
        Exp result = env.eval("((true 2) 1)");
        assertEquals("2", result.toString());
    }

    @Test
    void testFalseBuiltin() {
        Exp result = env.eval("((false 2) 1)");
        assertEquals("1", result.toString());
    }
}