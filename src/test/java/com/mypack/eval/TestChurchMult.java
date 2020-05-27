package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestChurchMult {

    @Test
    void testTwoTimesTwo() {
        Exp exp = LispParser.parse("((lambda (m n) (lambda (f x) (m (n f) x))) (lambda (f x) (f (f x))) (lambda (f x) (f (f x))))");
        List<Exp> result = Eval.iterEval(exp);
        assertTrue(eq("(lambda (f x) ((lambda (f x) (f (f x))) ((lambda (f x) (f (f x))) f) x))", result.get(1)));
        assertTrue(eq("(lambda (f x) (((lambda (f x) (f (f x))) f) (((lambda (f x) (f (f x))) f) x)))", result.get(2)));
        assertTrue(eq("(lambda (f x) ((lambda (x) (f (f x))) ((lambda (x) (f (f x))) x)))", result.get(3)));
        assertTrue(eq("(lambda (f x) (f (f ((lambda (x) (f (f x))) x))))", result.get(4)));
        assertTrue(eq("(lambda (f x) (f (f (f (f x)))))", result.get(5)));
    }

    @Test
    void testTwoTimesThree() {
        Exp exp = LispParser.parse("((lambda (m n) (lambda (f x) (m (n f) x))) (lambda (f x) (f (f x))) (lambda (f x) (f (f (f x)))))");
        List<Exp> result = Eval.iterEval(exp);
        assertTrue(eq("(lambda (f x) ((lambda (f x) (f (f x))) ((lambda (f x) (f (f (f x)))) f) x))", result.get(1)));
        assertTrue(eq("(lambda (f x) (((lambda (f x) (f (f (f x)))) f) (((lambda (f x) (f (f (f x)))) f) x)))", result.get(2)));
        assertTrue(eq("(lambda (f x) ((lambda (x) (f (f (f x)))) ((lambda (x) (f (f (f x)))) x)))", result.get(3)));
        assertTrue(eq("(lambda (f x) (f (f (f ((lambda (x) (f (f (f x)))) x)))))", result.get(4)));
        assertTrue(eq("(lambda (f x) (f (f (f (f (f (f x)))))))", result.get(5)));
    }
}