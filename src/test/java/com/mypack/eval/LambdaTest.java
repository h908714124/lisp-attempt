package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import com.mypack.vars.AlphaEquivalence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
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
        Exp exp = env.eval("(fact (lambda (f x) x))");
        Assertions.assertTrue(AlphaEquivalence.eq("(lambda (f x) (f x))", exp));
    }

    @Test
    void testFact1() throws IOException {
        String data = Files.readString(Paths.get("src/lisp/fact.lisp"));
        List<Exp> expressions = LispParser.parseList(data);
        Environment env = new Environment();
        env.load(expressions);
        Exp exp = env.eval("(fact (lambda (f x) (f x)))");
        Assertions.assertTrue(AlphaEquivalence.eq("(lambda (f x) (f x))", exp));
    }

    @Test
    void testFact2() throws IOException {
        String data = Files.readString(Paths.get("src/lisp/fact.lisp"));
        List<Exp> expressions = LispParser.parseList(data);
        Environment env = new Environment();
        env.load(expressions);
        List<Exp> exp = env.iterEval(LispParser.parse("(fact (lambda (f x) (f (f x))))"), 100);
        Assertions.assertTrue(AlphaEquivalence.eq("(lambda (f x) (f (f x)))", exp.get(exp.size() - 1)));
    }

    @Test
    void testFactsAboutFact() throws IOException {
        String data = Files.readString(Paths.get("src/lisp/direct.lisp"));
        List<Exp> expressions = LispParser.parseList(data);
        Environment env = new Environment();
        env.load(expressions);
        Exp exp = env.eval("(fact (lambda (f x) (f (f x))))");
        Assertions.assertTrue(AlphaEquivalence.eq("(lambda (f x) (f (f x)))", exp));
    }

    @Disabled("slow")
    @Test
    void testFact3() throws IOException {
        String data = Files.readString(Paths.get("src/lisp/fact.lisp"));
        List<Exp> expressions = LispParser.parseList(data);
        Environment env = new Environment();
        env.load(expressions);
        List<Exp> exp = env.iterEval(LispParser.parse("((Y fact1) (lambda (f x) (f (f (f x)))))"), 10000);
        Assertions.assertTrue(AlphaEquivalence.eq("(lambda (f x) (f (f (f (f (f (f x)))))))", exp.get(exp.size() - 1)));
    }
}