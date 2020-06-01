package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static com.mypack.test.AlphaEquivalence.assertEq;

class LambdaTest {

    private static final Environment ENV = new Environment();

    @BeforeAll
    static void setUp() {
        ENV.load(Path.of("src/clj/fact.clj"));
    }

    @Test
    void testFact0() {
        Exp exp = ENV.eval("(fact 0)");
        assertEq(ENV.eval("1"), exp);
    }

    @Test
    void testFact1() {
        Exp exp = ENV.eval("(fact 1)");
        assertEq(ENV.eval("1"), exp);
    }

    @Test
    void testFact2() {
        Exp exp = ENV.eval("(fact 2)");
        assertEq(ENV.eval("2"), exp);
    }

    @Test
    void testFact3() {
        Exp exp = ENV.eval("(fact 3)");
        assertEq(ENV.eval("6"), exp);
    }
}