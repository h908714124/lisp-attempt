package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TestChurchNumeral {

    @Test
    void testCheckZero0() {
        Exp exp = LispParser.parse("((lambda (n) (n (lambda (x) (lambda (a b) b)) (lambda (a b) a))) (lambda (f x) x))");
        List<Exp> result = Eval.iterEval(exp);
        Assertions.assertEquals("((lambda (f x) x) (lambda (x) (lambda (a b) b)) (lambda (a b) a))", result.get(1).toString());
        Assertions.assertEquals("(lambda (a b) a)", result.get(2).toString());
    }

    @Test
    void testCheckZero1() {
        Exp exp = LispParser.parse("((lambda (n) (n (lambda (x) (lambda (a b) b)) (lambda (a b) a))) (lambda (f x) (f x)))");
        List<Exp> result = Eval.iterEval(exp);
        Assertions.assertEquals("((lambda (f x) (f x)) (lambda (x) (lambda (a b) b)) (lambda (a b) a))", result.get(1).toString());
        Assertions.assertEquals("((lambda (x) (lambda (a b) b)) (lambda (a b) a))", result.get(2).toString());
        Assertions.assertEquals("(lambda (a b) b)", result.get(3).toString());
    }
}