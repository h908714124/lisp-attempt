package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.nio.file.Path;

import static com.mypack.test.TestUtil.assertEq;

@TestInstance(Lifecycle.PER_CLASS)
class LambdaTest {

    private final Environment env = new Environment();

    @BeforeAll
    void setUp() {
        env.load(Path.of("src/clj/fact.clj"));
    }

    @Test
    void testFact0() {
        Exp exp = env.eval("(fact 0)");
        assertEq(env, "1", exp);
    }

    @Test
    void testFact1() {
        Exp exp = env.eval("(fact 1)");
        assertEq(env, "1", exp);
    }

    @Test
    void testFact2() {
        Exp exp = env.eval("(fact 2)");
        assertEq(env, "2", exp);
    }

    @Test
    void testFact3() {
        Exp exp = env.eval("(fact 3)");
        assertEq(env, "6", exp);
    }

    @Test
    void testFact4() {
        Exp exp = env.eval("(fact 4)");
        assertEq(env, "24", exp);
    }
}