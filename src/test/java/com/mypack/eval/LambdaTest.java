package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.mypack.vars.AlphaEquivalence.eq;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LambdaTest {

    private static final Environment ENV = new Environment();

    @BeforeAll
    static void setUp() throws IOException {
        String data = Files.readString(Paths.get("src/lisp/fact.clj"));
        List<Exp> expressions = LispParser.parseList(data);
        ENV.load(expressions);
    }

    // https://tromp.github.io/cl/diagrams.html
    @Test
    void testFact0() {
        Exp exp = ENV.eval("(fact 0)");
        assertTrue(eq(ENV.eval("1"), exp));
    }

    @Test
    void testFact1() {
        Exp exp = ENV.eval("(fact 1)");
        assertTrue(eq(ENV.eval("1"), exp));
    }

    @Test
    void testFact2() {
        Exp exp = ENV.eval("(fact 2)");
        assertTrue(eq(ENV.eval("2"), exp));
    }

    @Test
    void testFact3() {
        Exp exp = ENV.eval("(fact 3)");
        assertTrue(eq(ENV.eval("6"), exp));
    }
}