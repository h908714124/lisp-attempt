package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import com.mypack.vars.AlphaEquivalence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestChurchBoolean {

    @Test
    void testChurchTrue() {
        Exp exp = LispParser.parse("((lambda (a b) a) (lambda (a b) a) (lambda (a b) b))");
        Exp result = exp.accept(new Eval());
        Assertions.assertTrue(AlphaEquivalence.test("(lambda (a b) a)", result));
    }

    @Test
    void testChurchFalse() {
        Exp exp = LispParser.parse("((lambda (a b) b) (lambda (a b) a) (lambda (a b) b))");
        Exp result = exp.accept(new Eval());
        Assertions.assertTrue(AlphaEquivalence.test("(lambda (a b) b)", result));
    }

    @Test
    void testFPChurchTrue1() {
        Exp exp = LispParser.parse("((lambda (f) ((lambda (x) (f x x)) (lambda (x) (f x x)))) (lambda (a b) a))");
        Exp result = exp.accept(new Eval());
        Assertions.assertTrue(AlphaEquivalence.test("((lambda (x) ((lambda (a b) a) x x)) (lambda (x) ((lambda (a b) a) x x)))", result));
    }

    // ((lambda (x) ((lambda (a b) a) x x))
    //  (lambda (x) ((lambda (a b) a) x x)))
    @Test
    void testFPChurchTrue2() {
        Exp exp = LispParser.parse("((lambda (x) ((lambda (a b) a) x x)) (lambda (x) ((lambda (a b) a) x x)))");
        Exp result = exp.accept(new Eval());
        Assertions.assertTrue(AlphaEquivalence.test("((lambda (a b) a) (lambda (x) ((lambda (a b) a) x x)) (lambda (x) ((lambda (a b) a) x x)))", result));
    }

    // ((lambda (a b) a)
    //  (lambda (x) ((lambda (a b) a) x x))
    //  (lambda (x) ((lambda (a b) a) x x)))
    @Test
    void testFPChurchTrue3() {
        Exp exp = LispParser.parse("((lambda (a b) a) (lambda (x) ((lambda (a b) a) x x)) (lambda (x) ((lambda (a b) a) x x)))");
        Exp result = exp.accept(new Eval());
        Assertions.assertTrue(AlphaEquivalence.test("(lambda (x) ((lambda (a b) a) x x))", result));
    }

    @Test
    void testFPChurchTrue4() {
        Exp exp = LispParser.parse("(lambda (x) ((lambda (a b) a) x x))");
        Exp result = exp.accept(new Eval());
        Assertions.assertTrue(AlphaEquivalence.test("(lambda (x) x)", result));
    }
}