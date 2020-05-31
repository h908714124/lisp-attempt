package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.Test;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestExtraArgument {

    @Test
    void testExtraArgument() {
        Exp exp = new Environment().eval("((lambda [x] (lambda [n] n)) (lambda [a b] a) (lambda [a b] b))");
        assertTrue(eq("(lambda [a b] b)", exp));
    }
}
