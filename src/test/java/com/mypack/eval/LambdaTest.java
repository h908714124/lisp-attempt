package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class LambdaTest {

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