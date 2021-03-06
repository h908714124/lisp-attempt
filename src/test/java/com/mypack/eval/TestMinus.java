package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(Lifecycle.PER_CLASS)
class TestMinus {

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
    void test5minus4() {
        Exp result = env.eval("(- 5 4 0 2 3)");
        assertEquals("3", result.toString());
    }

    @Test
    void test4minus5() {
        Exp result = env.eval("(- 4 5 10 20)");
        assertEquals("(- 4 5 10 20)", result.toString());
    }
}