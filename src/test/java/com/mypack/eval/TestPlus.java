package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(Lifecycle.PER_CLASS)
class TestPlus {

    private final Environment env = new Environment();

    @Test
    void testMixed() {
        Exp result = env.eval("(+ 4 (* 2 5) 3)");
        assertEquals("17", result.toString());
    }
}