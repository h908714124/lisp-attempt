package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import com.mypack.vars.AlphaEquivalence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TestChurchNumeral {

    @Test
    void testCheckZero0() {
        Exp exp = LispParser.parse("((lambda (n) (n (lambda (x) (lambda (a b) b)) (lambda (a b) a))) (lambda (f x) x))");
        List<Exp> result = Eval.iterEval(exp);
        Assertions.assertTrue(AlphaEquivalence.test("((lambda (f x) x) (lambda (x) (lambda (a b) b)) (lambda (a b) a))", result.get(1)));
        Assertions.assertTrue(AlphaEquivalence.test("(lambda (a b) a)", result.get(2)));
    }

    @Test
    void testCheckZero1() {
        Exp exp = LispParser.parse("((lambda (n) (n (lambda (x) (lambda (a b) b)) (lambda (a b) a))) (lambda (f x) (f x)))");
        List<Exp> result = Eval.iterEval(exp);
        Assertions.assertTrue(AlphaEquivalence.test("((lambda (f x) (f x)) (lambda (x) (lambda (a b) b)) (lambda (a b) a))", result.get(1)));
        Assertions.assertTrue(AlphaEquivalence.test("((lambda (x) (lambda (a b) b)) (lambda (a b) a))", result.get(2)));
        Assertions.assertTrue(AlphaEquivalence.test("(lambda (a b) b)", result.get(3)));
    }
}