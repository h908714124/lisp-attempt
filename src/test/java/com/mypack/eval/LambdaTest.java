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
    //  (lambda (x) ((lambda (a b)a) x x)))
    @Test
    void testFPChurchTrue3() {
        Exp exp = LispParser.parse("((lambda (a b) a) (lambda (x) ((lambda (a b) a) x x)) (lambda (x) ((lambda (a b) a) x x)))");
        Exp result = exp.accept(new Eval());
        Assertions.assertEquals("(lambda (x) ((lambda (a b) a) x x))", result.toString());
    }

    @Test
    void testFact1() {
        Exp exp = LispParser.parse("(((lambda (f) ((lambda (x) (f x x)) (lambda (x) (f x x)))) (lambda (f n) (zero? n 1 (* n (f (+ -1 n)))))) 2)");
        Exp result = exp.accept(new Eval());
        Assertions.assertEquals("(((lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x))) 2)", result.toString());
    }

    @Test
    void testFact2() {
        Exp exp = LispParser.parse("(((lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x))) 2)");
        Exp result = exp.accept(new Eval());
        Assertions.assertEquals("(((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x))) 2)", result.toString());
    }

    @Test
    void testFact3() {
        Exp exp = LispParser.parse("(((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x))) 2)");
        Exp result = exp.accept(new Eval());
        Assertions.assertEquals("((zero? (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) 1 (* (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) ((lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) (+ -1 (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)))))) 2)", result.toString());
    }

    @Test
    void testFact4() {
        Exp exp = LispParser.parse("((zero? (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) 1 (* (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) ((lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) (+ -1 (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)))))) 2)");
        Exp result = exp.accept(new Eval());
        Assertions.assertEquals("((* (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) ((lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) (+ -1 (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x))))) 2)", result.toString());
    }
}