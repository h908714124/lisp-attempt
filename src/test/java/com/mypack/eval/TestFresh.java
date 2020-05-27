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
        Assertions.assertEquals("(lambda (f x) (((lambda (f1 x1) (f1 (f1 x1))) (lambda (g h) (h (g f))) (lambda (u) x)) (lambda (u) u)))", result.get(1).toString());
        Assertions.assertEquals("(lambda (f x) (((lambda (g h) (h (g f))) ((lambda (g h) (h (g f))) (lambda (u) x))) (lambda (u) u)))", result.get(2).toString());
        Assertions.assertEquals("(lambda (f x) ((lambda (h) (h (((lambda (g1 h1) (h1 (g1 f))) (lambda (u) x)) f))) (lambda (u) u)))", result.get(3).toString());
        Assertions.assertEquals("(lambda (f x) ((lambda (u1) u1) (((lambda (g1 h1) (h1 (g1 f))) (lambda (u) x)) f)))", result.get(4).toString());
        Assertions.assertEquals("(lambda (f x) (((lambda (g1 h1) (h1 (g1 f))) (lambda (u) x)) f))", result.get(5).toString());
        Assertions.assertEquals("(lambda (f x) ((lambda (h1) (h1 ((lambda (u) x) f))) f))", result.get(6).toString());
        Assertions.assertEquals("(lambda (f x) (f ((lambda (u) x) f)))", result.get(7).toString());
        Assertions.assertEquals("(lambda (f x) (f x))", result.get(8).toString());
    }

}
