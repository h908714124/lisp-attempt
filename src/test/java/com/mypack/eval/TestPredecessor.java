package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.nio.file.Path;

import static com.mypack.test.TestUtil.assertEq;

@TestInstance(Lifecycle.PER_CLASS)
class TestPredecessor {

    private final Environment env = new Environment();

    @BeforeAll
    void setUp() {
        env.load(Path.of("src/clj/fact.clj"));
    }

    @Test
    void testPredecessorOfZero() {
        Exp result = env.eval("(pred 0)");
        assertEq(env, "0", result);
    }

    @Test
    void testPredecessorOfOne() {
        Exp result = env.eval("(pred 1)");
        assertEq(env, "0", result);
    }

    @Test
    void testPredecessorOfTwo() {
        Exp result = env.eval("(pred 2)");
        assertEq(env, "1", result);
    }

    @Test
    void testK() {
        Exp result = env.eval("((K 2) 1)");
        assertEq(env, "2", result);
    }
}