package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import com.mypack.vars.AlphaEquivalence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class LambdaTest {

    // https://tromp.github.io/cl/diagrams.html
    @Test
    void testFact0() throws IOException {
        String data = Files.readString(Paths.get("src/lisp/fact.lisp"));
        List<Exp> expressions = LispParser.parseList(data);
        Environment env = new Environment();
        env.load(expressions);
        Exp exp = env.eval("((Y fact) (lambda (f x) x))");
        Assertions.assertTrue(AlphaEquivalence.eq("(lambda (f x) (f x))", exp));
    }

    @Test
    void testFact1() throws IOException {
        String data = Files.readString(Paths.get("src/lisp/fact.lisp"));
        List<Exp> expressions = LispParser.parseList(data);
        Environment env = new Environment();
        env.load(expressions);
        Exp exp = env.eval("((Y fact) (lambda (f x) (f x)))");
        Assertions.assertTrue(AlphaEquivalence.eq("(lambda (f x) (f x))", exp));
    }

    @Test
    void testFact2() throws IOException {
        String data = Files.readString(Paths.get("src/lisp/fact.lisp"));
        List<Exp> expressions = LispParser.parseList(data);
        Environment env = new Environment();
        env.load(expressions);
        Exp exp = env.eval("((Y fact) (lambda (f x) (f (f x))))");
        Assertions.assertTrue(AlphaEquivalence.eq("(lambda (f x) (f (f x)))", exp));
    }
}