package com.mypack.eval;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestBadStuff {

    private final Environment env = new Environment();

    @Test
    void testExtraParens() {
        String irreducible = "((fn [f x] (f x)))";
        Assertions.assertEquals(irreducible, env.eval(irreducible).toString());
    }
}
