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
        Exp exp = LispParser.parse("((lambda (x12) ((lambda (f11 n2) ((n2 (lambda (x10) (lambda (a5 b5) b5)) " +
                "(lambda (a4 b4) a4)) (lambda (f10 x14) (f10 x14)) (lambda (f4 x11) (n2 ((f11 (lambda (f9 x13) " +
                "((n2 (lambda (g2 h2) (h2 (g2 f9))) (lambda (u4) x13)) (lambda (u5) u5)))) f4) x11)))) x12 x12)) " +
                "(lambda (x4) (lambda (a1 b1) b1)) (lambda (a b) a))");
        Exp result = Eval.iterEval(exp).get(1);
        Assertions.assertEquals("", result.toString());
    }
}