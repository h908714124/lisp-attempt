package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(Lifecycle.PER_CLASS)
class TestPartialApplication {

    private final Environment env = new Environment();

    @Test
    void testPartialApplication() {
        Exp result = env.eval("((fn [f x] (f x)) (fn [n] n))", 1);
        assertEquals("(fn [x] ((fn [n] n) x))", result.toString());
    }
}
