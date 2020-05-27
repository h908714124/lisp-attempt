package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestPredecessor {

    @Test
    void testPredecessorOfZero() {
        Exp exp = LispParser.parse("((lambda (n) (lambda (f x) ((n (lambda (g h) (h (g f))) (lambda (u) x)) (lambda (u1) u1)))) (lambda (f1 x1) x1))");
        List<Exp> result = Eval.iterEval(exp);
        assertTrue(eq("(lambda (f x) (((lambda (f1 x1) x1) (lambda (g h) (h (g f))) (lambda (u) x)) (lambda (u1) u1)))", result.get(1)));
        assertTrue(eq("(lambda (f x) ((lambda (u) x) (lambda (u1) u1)))", result.get(2)));
        assertTrue(eq("(lambda (f x) x)", result.get(3)));
    }

    @Test
    void testPredecessorOfOne() {
        Exp exp = LispParser.parse("((lambda (n) (lambda (f x) ((n (lambda (g h) (h (g f))) (lambda (u) x)) (lambda (u1) u1)))) (lambda (f1 x1) (f1 x1)))");
        List<Exp> result = Eval.iterEval(exp);
        assertTrue(eq("(lambda (f x) (((lambda (f1 x1) (f1 x1)) (lambda (g h) (h (g f))) (lambda (u) x)) (lambda (u1) u1)))", result.get(1)));
        assertTrue(eq("(lambda (f x) (((lambda (g h) (h (g f))) (lambda (u) x)) (lambda (u1) u1)))", result.get(2)));
        assertTrue(eq("(lambda (f x) ((lambda (h) (h ((lambda (u) x) f))) (lambda (u1) u1)))", result.get(3)));
        assertTrue(eq("(lambda (f x) ((lambda (u1) u1) ((lambda (u) x) f)))", result.get(4)));
        assertTrue(eq("(lambda (f x) ((lambda (u) x) f))", result.get(5)));
        assertTrue(eq("(lambda (f x) x)", result.get(6)));
    }

    @Test
    void testPredecessorOfTwo() {
        Exp exp = LispParser.parse("((lambda (n) (lambda (f x) ((n (lambda (g h) (h (g f))) (lambda (u) x)) (lambda (u1) u1)))) (lambda (f1 x1) (f1 (f1 x1))))");
        List<Exp> result = Eval.iterEval(exp);
        assertTrue(eq("(lambda (f x) (((lambda (f1 x1) (f1 (f1 x1))) (lambda (g h) (h (g f))) (lambda (u) x)) (lambda (u1) u1)))", result.get(1)));
        assertTrue(eq("(lambda (f x) (((lambda (g h) (h (g f))) ((lambda (g h) (h (g f))) (lambda (u) x))) (lambda (u1) u1)))", result.get(2)));
        assertTrue(eq("(lambda (f x) ((lambda (h) (h (((lambda (g1 h1) (h1 (g1 f))) (lambda (u) x)) f))) (lambda (u1) u1)))", result.get(3)));
        assertTrue(eq("(lambda (f x) ((lambda (u1) u1) (((lambda (g1 h1) (h1 (g1 f))) (lambda (u) x)) f)))", result.get(4)));
        assertTrue(eq("(lambda (f x) (((lambda (g1 h1) (h1 (g1 f))) (lambda (u) x)) f))", result.get(5)));
        assertTrue(eq("(lambda (f x) ((lambda (h1) (h1 ((lambda (u) x) f))) f))", result.get(6)));
        assertTrue(eq("(lambda (f x) (f ((lambda (u) x) f)))", result.get(7)));
        assertTrue(eq("(lambda (f x) (f x))", result.get(8)));
    }
}