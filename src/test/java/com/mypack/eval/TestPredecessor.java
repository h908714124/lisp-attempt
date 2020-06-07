package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(Lifecycle.PER_CLASS)
class TestPredecessor {

    private final Environment env = new Environment();

    @Test
    void testPredecessorOfZero() {
        Exp result = env.eval("(pred 0)");
        assertEquals("-1", result.toString());
    }

    @Test
    void testPredecessorOfOne() {
        Exp result = env.eval("(pred 1)");
        assertEquals("0", result.toString());
    }

    @Test
    void testPredecessorOfTwo() {
        Exp result = env.eval("(pred 2)");
        assertEquals("1", result.toString());
    }

    @Test
    void testK() {
        Exp result = env.eval("((K 2) 1)");
        assertEquals("2", result.toString());
    }
}