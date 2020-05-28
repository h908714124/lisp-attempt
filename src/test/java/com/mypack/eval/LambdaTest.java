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
        env.eval("(def fact (lambda (f n) ((zero? n) 1 (* n (f (pred n))))))");
        List<Exp> exp = env.iterEval("(((lambda (f) ((lambda (x) (f x x)) (lambda (x) (f x x)))) fact) 2)", 3);
        Assertions.assertEquals("(((lambda (x) (fact x x)) (lambda (x) (fact x x))) 2)", exp.get(1).toString());
        Assertions.assertEquals("((fact (lambda (x1) (fact x1 x1)) (lambda (x1) (fact x1 x1))) 2)", exp.get(2).toString());
        Assertions.assertEquals("(((lambda (f n) ((n (lambda (x) (lambda (a b) b)) (lambda (a b) a)) (lambda (f1 x) (f1 x)) (lambda (f1 x) (n ((f (lambda (f11 x11) ((n (lambda (g h) (h (g f11))) (lambda (u) x11)) (lambda (u1) u1)))) f1) x)))) (lambda (x1) ((lambda (f n) ((n (lambda (x) (lambda (a b) b)) (lambda (a b) a)) (lambda (f1 x) (f1 x)) (lambda (f1 x) (n ((f (lambda (f11 x11) ((n (lambda (g h) (h (g f11))) (lambda (u) x11)) (lambda (u1) u1)))) f1) x)))) x1 x1)) (lambda (x1) ((lambda (f n) ((n (lambda (x) (lambda (a b) b)) (lambda (a b) a)) (lambda (f1 x) (f1 x)) (lambda (f1 x) (n ((f (lambda (f11 x11) ((n (lambda (g h) (h (g f11))) (lambda (u) x11)) (lambda (u1) u1)))) f1) x)))) x1 x1))) 2)", exp.get(3).toString());
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