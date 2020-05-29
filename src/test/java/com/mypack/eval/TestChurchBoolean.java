package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestChurchBoolean {

    @Test
    void testChurchTrue() {
        Exp exp = LispParser.parse("((lambda (a b) a) (lambda (a b) a) (lambda (a b) b))");
        Exp result = Eval.iterEval(exp).get(1);
        assertTrue(eq("(lambda (a b) a)", result));
    }

    @Test
    void testChurchFalse() {
        Exp exp = LispParser.parse("((lambda (a b) b) (lambda (a b) a) (lambda (a b) b))");
        Exp result = Eval.iterEval(exp).get(1);
        assertTrue(eq("(lambda (a b) b)", result));
    }

    @Test
    void testFPChurchTrue1() throws IOException {
        String data = Files.readString(Paths.get("src/lisp/fact.lisp"));
        List<Exp> expressions = LispParser.parseList(data);
        Environment env = new Environment();
        env.load(expressions);
        List<Exp> result = env.iterEval("((lambda (f) ((lambda (x) (f x x)) (lambda (x) (f x x)))) (lambda (a b) a))", 10);
        assertEquals("((lambda (x) ((lambda (a b) a) x x)) (lambda (x) ((lambda (a b) a) x x)))", result.get(1).toString());
        assertEquals("((lambda (a b) a) (lambda (x) ((lambda (a1 b1) a1) x x)) (lambda (x) ((lambda (a1 b1) a1) x x)))", result.get(2).toString());
        assertEquals("(lambda (x) ((lambda (a1 b1) a1) x x))", result.get(3).toString());
        assertEquals("(lambda (x) x)", result.get(4).toString());
    }

    // ((lambda (x) ((lambda (a b) a) x x))
    //  (lambda (x) ((lambda (a b) a) x x)))
    @Test
    void testFPChurchTrue2() {
        Exp exp = LispParser.parse("((lambda (x) ((lambda (a b) a) x x)) (lambda (x) ((lambda (a b) a) x x)))");
        Exp result = Eval.iterEval(exp).get(1);
        assertTrue(eq("((lambda (a b) a) (lambda (x) ((lambda (a b) a) x x)) (lambda (x) ((lambda (a b) a) x x)))", result));
    }

    // ((lambda (a b) a)
    //  (lambda (x) ((lambda (a b) a) x x))
    //  (lambda (x) ((lambda (a b) a) x x)))
    @Test
    void testFPChurchTrue3() {
        Exp exp = LispParser.parse("((lambda (a b) a) (lambda (x) ((lambda (a b) a) x x)) (lambda (x) ((lambda (a b) a) x x)))");
        Exp result = Eval.iterEval(exp).get(1);
        assertTrue(eq("(lambda (x) ((lambda (a b) a) x x))", result));
    }

    @Test
    void testFPChurchTrue4() {
        Exp exp = LispParser.parse("(lambda (x) ((lambda (a b) a) x x))");
        Exp result = Eval.iterEval(exp).get(1);
        assertTrue(eq("(lambda (x) x)", result));
    }
}