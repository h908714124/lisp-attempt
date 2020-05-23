package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import com.mypack.util.AsValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

class LambdaTest {

    @Test
    void testChurchTrue() {
        Exp exp = LispParser.parse("((lambda (a b) a) 1 2)");
        Exp result = exp.accept(new Eval());
        Assertions.assertEquals(BigInteger.valueOf(1), AsValue.get(result).value());
    }

    @Test
    void testChurchFalse() {
        Exp exp = LispParser.parse("((lambda (a b) b) (+ 1 1) (+ 1 2))");
        Exp result = exp.accept(new Eval());
        Assertions.assertEquals("(+ 1 2)", result.toString());
    }

    @Test
    void testFixedPoint() {
        Exp exp = LispParser.parse("((lambda (f) (((lambda (x) (f x x))) ((lambda (x) (f x x))))) (lambda (a b) a))");
        Exp result = exp.accept(new Eval());
        Assertions.assertEquals("(((lambda (x) ((lambda (a b) a) x x))) ((lambda (x) ((lambda (a b) a) x x))))", result.toString());
    }

    @Test
    void testFixedPoint2() {
        Exp exp = LispParser.parse("(((lambda (x) ((lambda (a b) a) x x))) ((lambda (x) ((lambda (a b) a) x x))))");
        Exp result = exp.accept(new Eval());
        Assertions.assertEquals("?", result.toString());
    }
}