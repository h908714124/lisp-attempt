package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Test;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestChurchBoolean {

    @Test
    void testChurchTrue() {
        Exp exp = LispParser.parse("((fn [a b] a) (fn [a b] a) (fn [a b] b))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(fn [a b] a)", result));
    }

    @Test
    void testChurchFalse() {
        Exp exp = LispParser.parse("((fn [a b] b) (fn [a b] a) (fn [a b] b))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(fn [a b] b)", result));
    }

    @Test
    void testFPChurchTrue1() {
        Environment env = new Environment();
        Exp result = env.eval("((fn [f] ((fn [x] (f x x)) (fn [x] (f x x)))) (fn [a b] a))");
        assertTrue(eq("(fn [x] x)", result));
    }

    // ((fn (x) ((fn (a b) a) x x))
    //  (fn (x) ((fn (a b) a) x x)))
    @Test
    void testFPChurchTrue2() {
        Exp exp = LispParser.parse("((fn [x] ((fn [a b] a) x x)) (fn [x] ((fn [a b] a) x x)))");
        Exp result = new Environment().iterEval(exp, 1).get(1);
        assertTrue(eq("((fn [a b] a) (fn [x] ((fn [a b] a) x x)) (fn [x] ((fn [a b] a) x x)))", result));
    }

    // ((fn (a b) a)
    //  (fn (x) ((fn (a b) a) x x))
    //  (fn (x) ((fn (a b) a) x x)))
    @Test
    void testFPChurchTrue3() {
        Exp exp = LispParser.parse("((fn [a b] a) (fn [x] ((fn [a b] a) x x)) (fn [x] ((fn [a b] a) x x)))");
        Exp result = new Environment().iterEval(exp, 2).get(2);
        assertTrue(eq("(fn [x] ((fn [a b] a) x x))", result));
    }

    @Test
    void testFPChurchTrue4() {
        Exp exp = LispParser.parse("(fn [x] ((fn [a b] a) x x))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(fn [x] x)", result));
    }
}