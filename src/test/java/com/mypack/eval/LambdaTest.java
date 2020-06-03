package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Path;

import static com.mypack.test.TestUtil.assertEq;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LambdaTest {

    private Environment env;

    @BeforeAll
    void setUp() {
        env = new Environment();
        env.load(Path.of("src/clj/fact.clj"));
        Exp yfact = env.eval("((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))", 0);
        Exp def = env.eval("(Y fact_)", 0);
        env.addSubstitution(yfact, def);
    }

    @Test
    void testFact0() {
        env.setPrinting(true);
        Exp exp = env.eval("(fact 0)");
        env.setPrinting(false);
        assertEq(env, "1", exp);
    }

    @Test
    void testFact1() {
        env.setPrinting(true);
        Exp exp = env.eval("(fact 1)");
        env.setPrinting(false);
        assertEq(env, "1", exp);
    }

    @Test
    void testFact2() {
        env.setPrinting(true);
        Exp exp = env.eval("(fact 2)");
        env.setPrinting(false);
        assertEq(env, "2", exp);
    }

    @Test
    void testFact3() {
        env.setPrinting(true);
        Exp exp = env.eval("(fact 3)");
        env.setPrinting(false);
        assertEq(env, "6", exp);
    }

    @Test
    void testFact4() {
        Exp exp = env.eval("(fact 4)");
        assertEq(env, "24", exp);
    }
}