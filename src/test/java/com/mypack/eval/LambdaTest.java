package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LambdaTest {

    private Environment env;

    @BeforeAll
    void setUp() throws IOException {
        env = new Environment(new PrintStream(new FileOutputStream(Paths.get("/tmp/foo.txt").toFile())));
        env.load(Path.of("src/clj/fact.clj"));
    }

    @BeforeEach
    void setUpEach() {
        env.setPrinting(true);
    }

    @AfterEach
    void tearDown() {
        env.setPrinting(false);
    }

    @Test
    void testFact0() {
        Exp exp = env.eval("(fact 0)");
        assertEquals("1", exp.toString());
    }

    @Test
    void testFact1() {
        Exp exp = env.eval("(fact 1)");
        assertEquals("1", exp.toString());
    }

    @Test
    void testFact2() {
        Exp exp = env.eval("(fact 2)");
        assertEquals("2", exp.toString());
    }

    @Test
    void testFact3() {
        Exp exp = env.eval("(fact 3)");
        assertEquals("6", exp.toString());
    }

    @Test
    void testFact4() {
        Exp exp = env.eval("(fact 4)");
        assertEquals("24", exp.toString());
    }
}