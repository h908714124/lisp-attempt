package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.nio.file.Path;

import static com.mypack.test.TestUtil.assertEq;

@TestInstance(Lifecycle.PER_CLASS)
class TestIsZero {

    private final Environment env = new Environment();

    @BeforeAll
    void setUp() {
        env.load(Path.of("src/clj/fact.clj"));
    }

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
        assertEq(env, "true", env.eval("(zero? 0)"));
        assertEq(env, "false", env.eval("(zero? 1)"));
        assertEq(env, "false", env.eval("(zero? 2)"));
    }
}