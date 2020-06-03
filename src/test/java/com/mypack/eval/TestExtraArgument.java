package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.Test;

import static com.mypack.test.TestUtil.assertEq;

class TestExtraArgument {

    private final Environment env = new Environment();

    @Test
    void testExtraArgument() {
        Exp exp = env.eval("((fn [x] (fn [n] n)) (fn [a b] a) (fn [a b] b))");
        assertEq(env, env.eval("(fn [a b] b)"), exp);
    }
}
