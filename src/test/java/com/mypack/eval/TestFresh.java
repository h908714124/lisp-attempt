package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Test;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestFresh {

    @Test
    void testBadStuff() {
        Exp exp = LispParser.parse("((lambda (n) (lambda (f x) ((n (lambda (g h) (h (g f))) (lambda (u) x)) (lambda (u) u)))) (lambda (f x) (f (f x))))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(lambda (f x) (f x))", result));
    }
}
