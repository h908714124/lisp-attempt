package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import com.mypack.vars.AlphaEquivalence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TestChurchMult {

    @Test
    void testTwoTimesTwo() {
        Exp exp = LispParser.parse("((lambda (m n) (lambda (f x) (m (n f) x))) (lambda (f x) (f (f x))) (lambda (f x) (f (f x))))");
        List<Exp> result = Eval.iterEval(exp);
        Assertions.assertTrue(AlphaEquivalence.test("(lambda (f x) ((lambda (f x) (f (f x))) ((lambda (f x) (f (f x))) f) x))", result.get(1)));
        Assertions.assertTrue(AlphaEquivalence.test("(lambda (f x) (((lambda (f x) (f (f x))) f) (((lambda (f x) (f (f x))) f) x)))", result.get(2)));
        Assertions.assertTrue(AlphaEquivalence.test("(lambda (f x) ((lambda (x) (f (f x))) ((lambda (x) (f (f x))) x)))", result.get(3)));
        Assertions.assertTrue(AlphaEquivalence.test("(lambda (f x) (f (f ((lambda (x) (f (f x))) x))))", result.get(4)));
        Assertions.assertTrue(AlphaEquivalence.test("(lambda (f x) (f (f (f (f x)))))", result.get(5)));
    }

    @Test
    void testTwoTimesThree() {
        Exp exp = LispParser.parse("((lambda (m n) (lambda (f x) (m (n f) x))) (lambda (f x) (f (f x))) (lambda (f x) (f (f (f x)))))");
        List<Exp> result = Eval.iterEval(exp);
        Assertions.assertTrue(AlphaEquivalence.test("(lambda (f x) ((lambda (f x) (f (f x))) ((lambda (f x) (f (f (f x)))) f) x))", result.get(1)));
        Assertions.assertTrue(AlphaEquivalence.test("(lambda (f x) (((lambda (f x) (f (f (f x)))) f) (((lambda (f x) (f (f (f x)))) f) x)))", result.get(2)));
        Assertions.assertTrue(AlphaEquivalence.test("(lambda (f x) ((lambda (x) (f (f (f x)))) ((lambda (x) (f (f (f x)))) x)))", result.get(3)));
        Assertions.assertTrue(AlphaEquivalence.test("(lambda (f x) (f (f (f ((lambda (x) (f (f (f x)))) x)))))", result.get(4)));
        Assertions.assertTrue(AlphaEquivalence.test("(lambda (f x) (f (f (f (f (f (f x)))))))", result.get(5)));
    }
}