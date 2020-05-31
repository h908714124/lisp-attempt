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

class TestPredecessor {

    private static final Environment ENV = new Environment();

    @BeforeAll
    private static void setUp() throws IOException {
        String data = Files.readString(Paths.get("src/lisp/fact.clj"));
        List<Exp> expressions = LispParser.parseList(data);
        ENV.load(expressions);
    }

    @Test
    void testPredecessorOfZero() {
        Exp result = ENV.eval("(pred 0)");
        assertTrue(eq(ENV.eval("0"), result));
    }

    @Test
    void testPredecessorOfOne() {
        Exp result = ENV.eval("(pred 1)");
        assertTrue(eq(ENV.eval("0"), result));
    }

    @Test
    void testPredecessorOfTwo() {
        Exp result = ENV.eval("(pred 2)");
        assertTrue(eq(ENV.eval("1"), result));
    }
}