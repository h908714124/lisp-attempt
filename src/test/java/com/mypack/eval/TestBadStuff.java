package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.test.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestBadStuff {

    private final Environment env = new Environment();

    @Test
    void testExtraParens() {
        String irreducible = "((fn [f x] (f x)))";
        Assertions.assertEquals(irreducible, env.eval(irreducible).toString());
    }

    @Test
    void testNumWith1Arg() {
        Exp exp = env.eval("(fn [f x] (2 f))");
        TestUtil.assertEq(env, env.eval("(fn [f x] (fn [x1] (f (f x1))))"), exp);
    }

    @Test
    void testNumWith2Args() {
        Exp exp = env.eval("(fn [f x] (2 f x))", 1);
        Assertions.assertEquals("(fn [f x] (f (f x)))", exp.toString());
    }

    @Test
    void testNumWith3Args() {
        Exp exp = env.eval("(fn [f x] (2 f x 1))", 1);
        Assertions.assertEquals("(fn [f x] ((f (f x)) 1))", exp.toString());
    }
}
