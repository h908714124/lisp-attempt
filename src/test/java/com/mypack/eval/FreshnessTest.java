package com.mypack.eval;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FreshnessTest {

    private final Environment env = new Environment();

    @Test
    void testNotFresh() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> env.eval("((fn [a b] (fn [a x] a)) (fn [a b] a) (fn [a b] b))"));
        assertEquals("Non-fresh symbol in input: a", exception.getMessage());
    }
}