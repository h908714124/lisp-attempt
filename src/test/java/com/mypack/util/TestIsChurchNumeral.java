package com.mypack.util;

import com.mypack.eval.Environment;
import com.mypack.exp.Exp;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestIsChurchNumeral {

    private final Environment env = new Environment();

    @Test
    void test0() {
        Exp exp = env.eval("(fn [f x] x)");
        Optional<BigInteger> test = IsChurchNumeral.test(exp);
        assertTrue(test.isPresent());
        assertEquals(BigInteger.ZERO, test.get());
    }

    @Test
    void test1() {
        Exp exp = env.eval("(fn [f x] (f x))");
        Optional<BigInteger> test = IsChurchNumeral.test(exp);
        assertTrue(test.isPresent());
        assertEquals(BigInteger.ONE, test.get());
    }

    @Test
    void test3() {
        Exp exp = env.eval("(fn [f x] (f (f (f x))))");
        Optional<BigInteger> test = IsChurchNumeral.test(exp);
        assertTrue(test.isPresent());
        assertEquals(BigInteger.valueOf(3), test.get());
    }

    @Test
    void testFalse() {
        Exp exp = env.eval("(fn [f x] (f (f (x x))))");
        Optional<BigInteger> test = IsChurchNumeral.test(exp);
        assertFalse(test.isPresent());
    }
}