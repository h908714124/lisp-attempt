package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LambdaTest {

    // https://tromp.github.io/cl/diagrams.html
    @Test
    void testFact0() throws IOException {
        String data = Files.readString(Paths.get("src/lisp/fact.lisp"));
        List<Exp> expressions = LispParser.parseList(data);
        Environment env = new Environment();
        env.load(expressions);
        Exp exp = env.eval("(fact 0)");
        assertTrue(eq(env.eval("1"), exp));
    }

    @Test
    void testFact1() throws IOException {
        String data = Files.readString(Paths.get("src/lisp/fact.lisp"));
        List<Exp> expressions = LispParser.parseList(data);
        Environment env = new Environment();
        env.load(expressions);
        Exp exp = env.eval("(fact 1)");
        assertTrue(eq(env.eval("1"), exp));
    }

    @Test
    void testFact2() throws IOException {
        String data = Files.readString(Paths.get("src/lisp/fact.lisp"));
        List<Exp> expressions = LispParser.parseList(data);
        Environment env = new Environment();
        env.load(expressions);
        Exp exp = env.eval("(fact 2)");
        assertTrue(eq(env.eval("2"), exp));
    }

    @Test
    void testFactsAboutFact() throws IOException {
        String data = Files.readString(Paths.get("src/lisp/direct.lisp"));
        List<Exp> expressions = LispParser.parseList(data);
        Environment env = new Environment();
        env.load(expressions);
        Exp exp = env.eval("(fact 2)");
        assertTrue(eq(env.eval("2"), exp));
    }

    @Test
    void testFact3() throws IOException {
        String data = Files.readString(Paths.get("src/lisp/fact.lisp"));
        List<Exp> expressions = LispParser.parseList(data);
        Environment env = new Environment();
        env.load(expressions);
        Exp exp = env.eval("(fact 3)");
        assertTrue(eq(env.eval("6"), exp));
    }
}