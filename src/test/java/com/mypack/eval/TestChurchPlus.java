package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TestChurchPlus {

    @Test
    void testZeroPlusZero() {
        Exp exp = LispParser.parse("((lambda (m n) (lambda (f x) (m f (n f x)))) (lambda (f x) x) (lambda (f x) x))");
        List<Exp> result = Eval.iterEval(exp);
        Assertions.assertEquals("(lambda (f x) ((lambda (f x) x) f ((lambda (f x) x) f x)))", result.get(1).toString());
        Assertions.assertEquals("(lambda (f x) ((lambda (f x) x) f x))", result.get(2).toString());
        Assertions.assertEquals("(lambda (f x) x)", result.get(3).toString());
    }

    @Test
    void testZeroPlusOne() {
        Exp exp = LispParser.parse("((lambda (m n) (lambda (f x) (m f (n f x)))) (lambda (f x) x) (lambda (f x) (f x)))");
        List<Exp> result = Eval.iterEval(exp);
        Assertions.assertEquals("(lambda (f x) ((lambda (f x) x) f ((lambda (f x) (f x)) f x)))", result.get(1).toString());
        Assertions.assertEquals("(lambda (f x) ((lambda (f x) (f x)) f x))", result.get(2).toString());
        Assertions.assertEquals("(lambda (f x) (f x))", result.get(3).toString());
    }

    @Test
    void testOnePlusZero() {
        Exp exp = LispParser.parse("((lambda (m n) (lambda (f x) (m f (n f x)))) (lambda (f x) (f x)) (lambda (f x) x))");
        List<Exp> result = Eval.iterEval(exp);
        Assertions.assertEquals("(lambda (f x) ((lambda (f x) (f x)) f ((lambda (f x) x) f x)))", result.get(1).toString());
        Assertions.assertEquals("(lambda (f x) (f ((lambda (f x) x) f x)))", result.get(2).toString());
        Assertions.assertEquals("(lambda (f x) (f x))", result.get(3).toString());
    }

    @Test
    void testOnePlusOne() {
        Exp exp = LispParser.parse("((lambda (m n) (lambda (f x) (m f (n f x)))) (lambda (f x) (f x)) (lambda (f x) (f x)))");
        List<Exp> result = Eval.iterEval(exp);
        Assertions.assertEquals("(lambda (f x) ((lambda (f x) (f x)) f ((lambda (f x) (f x)) f x)))", result.get(1).toString());
        Assertions.assertEquals("(lambda (f x) (f ((lambda (f x) (f x)) f x)))", result.get(2).toString());
        Assertions.assertEquals("(lambda (f x) (f (f x)))", result.get(3).toString());
    }
}