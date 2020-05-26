package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TestFresh {

    @Test
    void testBadStuff() {
        Exp exp = LispParser.parse("((lambda (n) (lambda (f x) ((n (lambda (g h) (h (g f))) (lambda (u) x)) (lambda (u) u)))) (lambda (f x) (f (f x))))");
        List<Exp> result = Eval.iterEval(exp);
        Assertions.assertEquals("(lambda (f x) (((lambda (f x) (f (f x))) (lambda (g h) (h (g f))) (lambda (u) x)) (lambda (u) u)))", result.get(1).toString());
        Assertions.assertEquals("(lambda (f x) (((lambda (g h) (h (g f1))) ((lambda (g h) (h (g f1))) (lambda (u) x1))) (lambda (u) u)))", result.get(2).toString());
        Assertions.fail("Bad stuff: f1 is undefined"); // why does this happen?
    }

}
