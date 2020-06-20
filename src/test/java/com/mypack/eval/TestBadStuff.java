package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestBadStuff {

    private final Environment env = new Environment();

    @Test
    void testExtraParens() {
        String irreducible = "((f x))";
        Assertions.assertEquals(irreducible, env.eval(irreducible).toString());
    }

    @Test
    void test1() {
        Exp exp = env.eval("(1 f x)", 1);
        Assertions.assertEquals("(f x)", exp.toString());
    }

    @Test
    void test2() {
        Exp exp = env.eval("(2 f x)", 1);
        Assertions.assertEquals("(f (f x))", exp.toString());
    }

    @Test
    void test1with3Args() {
        Exp exp = env.eval("(1 f x x)", 1);
        Assertions.assertEquals("(f x x)", exp.toString());
    }

    @Test
    void test2with3Args() {
        Exp exp = env.eval("(2 f x x)", 1);
        Assertions.assertEquals("(f (f x) x)", exp.toString());
    }

    @Test
    void testFalse2args() {
        Exp exp = env.eval("(false f x)", 1);
        Assertions.assertEquals("x", exp.toString());
    }

    @Test
    void testFalse3args() {
        Exp exp = env.eval("(false f x 1)", 1);
        Assertions.assertEquals("(x 1)", exp.toString());
    }

    @Test
    void testY1() {
        Exp exp = env.eval("(Y 2)", 1);
        Assertions.assertEquals("(2 (Y 2))", exp.toString());
    }

    @Test
    void testY2() {
        Exp exp = env.eval("(Y 2 3)", 1);
        Assertions.assertEquals("(2 (Y 2) 3)", exp.toString());
    }

    @Test
    void testY3() {
        Exp exp = env.eval("(Y 2 3 4)", 1);
        Assertions.assertEquals("(2 (Y 2) 3 4)", exp.toString());
    }
}
