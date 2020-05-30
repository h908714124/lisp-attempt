package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestPartialApplication {

    @Test
    void testPartialApplication() {
        Exp exp = LispParser.parse("((lambda (f x) (f x)) (lambda (n) n))");
        List<Exp> result = new Environment().iterEval(exp, 1);
        assertTrue(eq("(lambda (x) ((lambda (n) n) x))", result.get(1)));
    }
}
