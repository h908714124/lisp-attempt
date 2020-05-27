package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestChurchNumeral {

    @Test
    void testCheckZero0() {
        Exp exp = LispParser.parse("((lambda (n) (n (lambda (x) (lambda (a b) b)) (lambda (a b) a))) (lambda (f x) x))");
        List<Exp> result = Eval.iterEval(exp);
        assertTrue(eq("((lambda (f x) x) (lambda (x) (lambda (a b) b)) (lambda (a b) a))", result.get(1)));
        assertTrue(eq("(lambda (a b) a)", result.get(2)));
    }

    @Test
    void testCheckZero1() {
        Exp exp = LispParser.parse("((lambda (n) (n (lambda (x) (lambda (a b) b)) (lambda (a b) a))) (lambda (f x) (f x)))");
        List<Exp> result = Eval.iterEval(exp);
        assertTrue(eq("((lambda (f x) (f x)) (lambda (x) (lambda (a b) b)) (lambda (a b) a))", result.get(1)));
        assertTrue(eq("((lambda (x) (lambda (a b) b)) (lambda (a b) a))", result.get(2)));
        assertTrue(eq("(lambda (a b) b)", result.get(3)));
    }
}