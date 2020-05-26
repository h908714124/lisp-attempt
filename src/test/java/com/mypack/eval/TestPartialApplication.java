package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TestPartialApplication {

    @Test
    void testPartialApplication() {
        Exp exp = LispParser.parse("((lambda (f x) (f x)) (lambda (n) n))");
        List<Exp> result = Eval.iterEval(exp);
        Assertions.assertEquals("(lambda (x) ((lambda (n) n) x))", result.get(1).toString());
    }
}
