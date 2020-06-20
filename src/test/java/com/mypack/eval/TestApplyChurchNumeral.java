package com.mypack.eval;

import com.mypack.exp.Exp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestApplyChurchNumeral {

    private final Environment env = new Environment();

    @Test
    void test10appliedTo20() {
        Exp result = env.eval("(10 20)");
        assertEquals("10240000000000", result.toString());
    }

    @Test
    void test333() {
        Exp result = env.eval("(3 3 3)");
        assertEquals("7625597484987", result.toString());
    }

    @Test
    void test2ff32() {
        Exp result = env.eval("(2 (fn [f x] (f (f x))) 3 2)");
        assertEquals("2417851639229258349412352", result.toString());
    }

    @Test
    void test2232() {
        Exp result = env.eval("(2 2 3 2)"); // 2^(3^(2^2))
        assertEquals("2417851639229258349412352", result.toString());
    }

    @Test
    void test3332() {
        Exp result = env.eval("(3 (3 (3 (3 2))))"); // ((2^3)^3^3)^3
        assertEquals("2417851639229258349412352", result.toString());
    }
}
