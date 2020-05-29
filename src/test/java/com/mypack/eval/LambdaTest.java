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
        List<Exp> exp = env.iterEval("(((lambda (f) ((lambda (x) (f x x)) (lambda (x) (f x x)))) fact) 2)", 2);
        Assertions.assertEquals("(((lambda (x) ((lambda (f4 n) ((n (lambda (x4) (lambda (a1 b1) b1)) (lambda (a b) a)) (lambda (f3 x3) (f3 x3)) (lambda (f5 x5) (n ((f4 (lambda (f2 x2) ((n (lambda (g h) (h (g f2))) (lambda (u) x2)) (lambda (u1) u1)))) f5) x5)))) x x)) (lambda (x) ((lambda (f4 n) ((n (lambda (x4) (lambda (a1 b1) b1)) (lambda (a b) a)) (lambda (f3 x3) (f3 x3)) (lambda (f5 x5) (n ((f4 (lambda (f2 x2) ((n (lambda (g h) (h (g f2))) (lambda (u) x2)) (lambda (u1) u1)))) f5) x5)))) x x))) (lambda (f1 x1) (f1 (f1 x1))))", exp.get(1).toString());
        Assertions.assertEquals("(((lambda (f4 n) ((n (lambda (x4) (lambda (a1 b1) b1)) (lambda (a b) a)) (lambda (f3 x3) (f3 x3)) (lambda (f5 x5) (n ((f4 (lambda (f2 x2) ((n (lambda (g h) (h (g f2))) (lambda (u) x2)) (lambda (u1) u1)))) f5) x5)))) (lambda (x) ((lambda (f7 n1) ((n1 (lambda (x7) (lambda (a3 b3) b3)) (lambda (a2 b2) a2)) (lambda (f6 x6) (f6 x6)) (lambda (f8 x8) (n1 ((f7 (lambda (f1 x1) ((n1 (lambda (g1 h1) (h1 (g1 f1))) (lambda (u2) x1)) (lambda (u3) u3)))) f8) x8)))) x x)) (lambda (x) ((lambda (f7 n1) ((n1 (lambda (x7) (lambda (a3 b3) b3)) (lambda (a2 b2) a2)) (lambda (f6 x6) (f6 x6)) (lambda (f8 x8) (n1 ((f7 (lambda (f1 x1) ((n1 (lambda (g1 h1) (h1 (g1 f1))) (lambda (u2) x1)) (lambda (u3) u3)))) f8) x8)))) x x))) (lambda (f1 x1) (f1 (f1 x1))))", exp.get(2).toString());
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
        Exp exp = LispParser.parse("((lambda (x12) ((lambda (f11 n2) ((n2 (lambda (x10) (lambda (a5 b5) b5)) " +
                "(lambda (a4 b4) a4)) (lambda (f10 x14) (f10 x14)) (lambda (f4 x11) (n2 ((f11 (lambda (f9 x13) " +
                "((n2 (lambda (g2 h2) (h2 (g2 f9))) (lambda (u4) x13)) (lambda (u5) u5)))) f4) x11)))) x12 x12)) " +
                "(lambda (x4) (lambda (a1 b1) b1)) (lambda (a b) a))");
        Exp result = Eval.iterEval(exp).get(1);
        Assertions.assertEquals("", result.toString());
    }
}