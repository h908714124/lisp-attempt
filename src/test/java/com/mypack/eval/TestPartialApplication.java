package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import com.mypack.vars.AlphaEquivalence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TestPartialApplication {

    @Test
    void testPartialApplication() {
        Exp exp = LispParser.parse("((lambda (f x) (f x)) (lambda (n) n))");
        List<Exp> result = Eval.iterEval(exp);
        Assertions.assertTrue(AlphaEquivalence.test("(lambda (x) ((lambda (n) n) x))", result.get(1)));
    }
}
