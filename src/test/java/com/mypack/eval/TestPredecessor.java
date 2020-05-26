package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TestPredecessor {

    @Test
    void testPredecessorOfZero() {
        Exp exp = LispParser.parse("((lambda (n) (lambda (f x) ((n (lambda (g h) (h (g f))) (lambda (u) x)) (lambda (u) u)))) (lambda (f x) x))");
        List<Exp> result = Eval.iterEval(exp);
        Assertions.assertEquals("(lambda (f x) (((lambda (f x) x) (lambda (g h) (h (g f))) (lambda (u) x)) (lambda (u) u)))", result.get(1).toString());
        Assertions.assertEquals("(lambda (f x) ((lambda (u) x) (lambda (u) u)))", result.get(2).toString());
        Assertions.assertEquals("(lambda (f x) x)", result.get(3).toString());
    }

    @Test
    void testPredecessorOfOne() {
        Exp exp = LispParser.parse("((lambda (n) (lambda (f x) ((n (lambda (g h) (h (g f))) (lambda (u) x)) (lambda (u) u)))) (lambda (f x) (f x)))");
        List<Exp> result = Eval.iterEval(exp);
        Assertions.assertEquals("(lambda (f x) (((lambda (f x) (f x)) (lambda (g h) (h (g f))) (lambda (u) x)) (lambda (u) u)))", result.get(1).toString());
        Assertions.assertEquals("(lambda (f x) (((lambda (g h) (h (g f))) (lambda (u) x)) (lambda (u) u)))", result.get(2).toString());
        Assertions.assertEquals("(lambda (f x) ((lambda (h) (h ((lambda (u) x) f))) (lambda (u) u)))", result.get(3).toString());
        Assertions.assertEquals("(lambda (f x) ((lambda (u) u) ((lambda (u) x) f)))", result.get(4).toString());
        Assertions.assertEquals("(lambda (f x) ((lambda (u) x) f))", result.get(5).toString());
        Assertions.assertEquals("(lambda (f x) x)", result.get(6).toString());
    }

    @Test
    void testPredecessorOfTwo() {
        Exp exp = LispParser.parse("((lambda (n) (lambda (f x) ((n (lambda (g h) (h (g f))) (lambda (u) x)) (lambda (v) v)))) (lambda (f x) (f (f x))))");
        List<Exp> result = Eval.iterEval(exp, 4);
        Assertions.assertEquals("(lambda (f x) (((lambda (f x) (f (f x))) (lambda (g h) (h (g f))) (lambda (u) x)) (lambda (v) v)))", result.get(1).toString());
        Assertions.assertEquals("(lambda (f x) (((lambda (g h) (h (g f))) ((lambda (g h) (h (g f))) (lambda (u) x))) (lambda (v) v)))", result.get(2).toString());
        Assertions.assertEquals("(lambda (f x) ((lambda (h) (h (((lambda (g h) (h (g f))) (lambda (u) x)) f))) (lambda (v) v)))", result.get(3).toString());
        Assertions.assertEquals("(lambda (f x) ((lambda (v) v) (((lambda (g (lambda (v) v)) ((lambda (v) v) (g f))) (lambda (u) x)) f)))", result.get(4).toString());
    }

    @Test
    void test() {
        Exp exp = LispParser.parse("(lambda (f x) ((lambda (h) (h (((lambda (g h1) (h1 (g f))) (lambda (u) x)) f))) (lambda (v) v)))");
        List<Exp> result = Eval.iterEval(exp, 5);
        Assertions.assertEquals("(lambda (f x) ((lambda (v) v) (((lambda (g h1) (h1 (g f))) (lambda (u) x)) f)))", result.get(1).toString());
        Assertions.assertEquals("(lambda (f x) (((lambda (g h1) (h1 (g f))) (lambda (u) x)) f))", result.get(2).toString());
        Assertions.assertEquals("(lambda (f x) ((lambda (h1) (h1 ((lambda (u) x) f))) f))", result.get(3).toString());
        Assertions.assertEquals("(lambda (f x) (f ((lambda (u) x) f)))", result.get(4).toString());
        Assertions.assertEquals("(lambda (f x) (f x))", result.get(5).toString());
    }
}