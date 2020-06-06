package com.mypack.test;

import com.mypack.eval.Environment;
import com.mypack.exp.Exp;
import com.mypack.vars.AlphaEquivalence;
import org.junit.jupiter.api.Assertions;

public class TestUtil {

    public static void assertEq(Environment env, Exp exp1, Exp exp2) {
        Boolean result = AlphaEquivalence.eq(exp1, env.eval(exp2));
        if (!result) {
            String message = String.format("expected: %s but was: %s", exp1, exp2);
            Assertions.fail(message);
        }
    }

    public static void assertEq(Environment env, String symbol, Exp exp2) {
        assertEq(env, env.lookup(symbol), exp2);
    }
}
