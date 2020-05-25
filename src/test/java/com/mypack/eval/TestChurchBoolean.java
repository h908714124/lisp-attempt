package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestChurchBoolean {

    @Test
    void testChurchTrue() {
        Exp exp = LispParser.parse("((lambda (a b) a) (lambda (a b) a) (lambda (a b) b))");
        Exp result = exp.accept(new Eval());
        Assertions.assertEquals("(lambda (a b) a)", result.toString());
    }

    @Test
    void testChurchFalse() {
        Exp exp = LispParser.parse("((lambda (a b) b) (lambda (a b) a) (lambda (a b) b))");
        Exp result = exp.accept(new Eval());
        Assertions.assertEquals("(lambda (a b) b)", result.toString());
    }

    @Test
    void testFPChurchTrue1() {
        Exp exp = LispParser.parse("((lambda (f) ((lambda (x) (f x x)) (lambda (x) (f x x)))) (lambda (a b) a))");
        Exp result = exp.accept(new Eval());
        Assertions.assertEquals("((lambda (x) ((lambda (a b) a) x x)) (lambda (x) ((lambda (a b) a) x x)))", result.toString());
    }

    // ((lambda (x) ((lambda (a b) a) x x))
    //  (lambda (x) ((lambda (a b) a) x x)))
    @Test
    void testFPChurchTrue2() {
        Exp exp = LispParser.parse("((lambda (x) ((lambda (a b) a) x x)) (lambda (x) ((lambda (a b) a) x x)))");
        Exp result = exp.accept(new Eval());
        Assertions.assertEquals("((lambda (a b) a) (lambda (x) ((lambda (a b) a) x x)) (lambda (x) ((lambda (a b) a) x x)))", result.toString());
    }

    // ((lambda (a b) a)
    //  (lambda (x) ((lambda (a b) a) x x))
    //  (lambda (x) ((lambda (a b) a) x x)))
    @Test
    void testFPChurchTrue3() {
        Exp exp = LispParser.parse("((lambda (a b) a) (lambda (x) ((lambda (a b) a) x x)) (lambda (x) ((lambda (a b) a) x x)))");
        Exp result = exp.accept(new Eval());
        Assertions.assertEquals("(lambda (x) ((lambda (a b) a) x x))", result.toString());
    }

    @Test
    void testFPChurchTrue4() {
        Exp exp = LispParser.parse("(lambda (x) ((lambda (a b) a) x x))");
        Exp result = exp.accept(new Eval());
        Assertions.assertEquals("(lambda (x) x)", result.toString());
    }
}