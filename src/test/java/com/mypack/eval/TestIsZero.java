package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static com.mypack.test.TestUtil.assertEq;

class TestIsZero {

    private static final Environment ENV = new Environment();

    @BeforeAll
    static void setUp() {
        ENV.load(Path.of("src/clj/fact.clj"));
    }

    @Test
    void testCheckZero0() {
        Exp result = ENV.eval("((fn [n] (n (fn [x] (fn [a b] b)) (fn [a b] a))) (fn [f x] x))");
        assertEq(ENV, ENV.eval("(fn [a b] a)"), result);
    }

    @Test
    void testCheckZero1() {
        Exp result = ENV.eval("((fn [n] (n (fn [x] (fn [a b] b)) (fn [a b] a))) (fn [f x] (f x)))");
        assertEq(ENV, ENV.eval("(fn [a b] b)"), result);
    }

    @Test
    void testDef() {
        assertEq(ENV, "true", ENV.eval("(zero? 0)"));
        assertEq(ENV, "false", ENV.eval("(zero? 1)"));
        assertEq(ENV, "false", ENV.eval("(zero? 2)"));
    }
}