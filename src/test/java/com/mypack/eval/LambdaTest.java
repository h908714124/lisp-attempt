package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class LambdaTest {

    @Test
    void testFact() throws IOException {
        String data = Files.readString(Paths.get("src/lisp/fact.lisp"));
        List<Exp> expressions = LispParser.parseList(data);
        Environment env = new Environment();
        env.load(expressions);
        List<Exp> exp = env.iterEval("(((lambda (f) ((lambda (x) (f x x)) (lambda (x1) (f x1 x1)))) fact) 2)", 19);
        Assertions.assertEquals("", exp.get(1).toString());
        Assertions.assertEquals("", exp.get(2).toString());
        Assertions.assertEquals("", exp.get(3).toString());
        Assertions.assertEquals("", exp.get(4).toString());
        Assertions.assertEquals("", exp.get(5).toString());
        Assertions.assertEquals("", exp.get(7).toString());
        Assertions.assertEquals("", exp.get(8).toString());
        Assertions.assertEquals("", exp.get(9).toString());
        Assertions.assertEquals("", exp.get(10).toString());
        Assertions.assertEquals("", exp.get(11).toString());
        Assertions.assertEquals("", exp.get(12).toString());
        Assertions.assertEquals("", exp.get(13).toString());
        Assertions.assertEquals("", exp.get(14).toString());
        Assertions.assertEquals("", exp.get(15).toString());
        Assertions.assertEquals("", exp.get(16).toString());
        Assertions.assertEquals("", exp.get(17).toString());
        Assertions.assertEquals("", exp.get(18).toString());
        Assertions.assertEquals("", exp.get(19).toString());
    }

    @Test
    void testFact1() {
        Exp exp = LispParser.parse("(((lambda (f) ((lambda (x) (f x x)) (lambda (x) (f x x)))) (lambda (f n) (zero? n 1 (* n (f (pred n)))))) 2)");
        Exp result = Eval.iterEval(exp).get(1);
        Assertions.assertEquals("(((lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x))) 2)", result.toString());
    }

    @Test
    void testFact2() {
        Exp exp = LispParser.parse("(((lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x))) 2)");
        Exp result = Eval.iterEval(exp).get(1);
        Assertions.assertEquals("(((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x))) 2)", result.toString());
    }

    @Test
    void testFact3() {
        Exp exp = LispParser.parse("(((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x))) 2)");
        Exp result = Eval.iterEval(exp).get(1);
        Assertions.assertEquals("((zero? (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) 1 (* (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) ((lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) (+ -1 (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)))))) 2)", result.toString());
    }

    @Test
    void testFact4() {
        Exp exp = LispParser.parse("((zero? (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) 1 (* (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) ((lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) (+ -1 (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)))))) 2)");
        Exp result = Eval.iterEval(exp).get(1);
        Assertions.assertEquals("((* (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) ((lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x)) (+ -1 (lambda (x) ((lambda (f n) (zero? n 1 (* n (f (+ -1 n))))) x x))))) 2)", result.toString());
    }
}