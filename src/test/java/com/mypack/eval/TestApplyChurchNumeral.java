package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestApplyChurchNumeral {

    private final Environment env = new Environment();

    @Test
    void test10appliedTo20() {
        Exp result = env.eval("(10 20)");
        assertEquals("(10 20)", result.toString());
    }

    @Test
    void test1appliedTo20and30() {
        Exp result = env.eval("(1 10 20)");
        assertEquals("(10 20)", result.toString());
    }

    @Test
    void test10appliedTo20and30() {
        Exp result = env.eval("(10 20 30)");
        assertEquals("(20 (20 (20 (20 (20 (20 (20 (20 (20 (20 30))))))))))", result.toString());
    }
}
