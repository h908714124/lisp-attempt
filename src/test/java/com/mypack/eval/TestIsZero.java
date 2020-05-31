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

class TestIsZero {

    @Test
    void testCheckZero0() {
        Exp exp = LispParser.parse("((fn [n] (n (fn [x] (fn [a b] b)) (fn [a b] a))) (fn [f x] x))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(fn [a b] a)", result));
    }

    @Test
    void testCheckZero1() {
        Exp exp = LispParser.parse("((fn [n] (n (fn [x] (fn [a b] b)) (fn [a b] a))) (fn [f x] (f x)))");
        Exp result = new Environment().eval(exp);
        assertTrue(eq("(fn [a b] b)", result));
    }

    @Test
    void testDef() throws IOException {
        String data = Files.readString(Paths.get("src/lisp/fact.lisp"));
        List<Exp> expressions = LispParser.parseList(data);
        Environment env = new Environment();
        env.load(expressions);
        assertTrue(eq(env.eval("true"), env.eval("(zero? 0)")));
        assertTrue(eq(env.eval("false"), env.eval("(zero? 1)")));
        assertTrue(eq(env.eval("false"), env.eval("(zero? 2)")));
    }
}