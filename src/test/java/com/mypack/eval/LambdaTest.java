package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;

import static com.mypack.test.AlphaEquivalence.assertEq;

class LambdaTest {

    private static final Environment ENV = environment();

    private static Environment environment()  {
        try {
            return new Environment(new PrintStream(new FileOutputStream("/tmp/foo.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @BeforeAll
    static void setUp() {
        ENV.load(Path.of("src/clj/fact.clj"));
    }

    @Test
    void testFact0() {
        ENV.setPrinting(true);
        Exp exp = ENV.eval("(fact 0)");
        ENV.setPrinting(false);
        assertEq(ENV, "1", exp);
    }

    @Test
    void testFact1() {
        ENV.setPrinting(true);
        Exp exp = ENV.eval("(fact 1)");
        ENV.setPrinting(false);
        assertEq(ENV, "1", exp);
    }

    @Test
    void testFact2() {
        ENV.setPrinting(true);
        Exp exp = ENV.eval("(fact 2)");
        ENV.setPrinting(false);
        assertEq(ENV, "2", exp);
    }

    @Test
    void testFact3() {
        ENV.setPrinting(true);
        Exp exp = ENV.eval("(fact 3)");
        ENV.setPrinting(false);
        assertEq(ENV, "6", exp);
    }
}