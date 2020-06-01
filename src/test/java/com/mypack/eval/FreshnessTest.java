package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.Symbol;
import com.mypack.vars.Freshness;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FreshnessTest {

    private static final Environment ENV = new Environment();

    @Test
    void testFresh() {
        Exp exp = ENV.eval("((fn [a b] b) (fn [a b] a) (fn [a b] b))", 0);
        Optional<Symbol> test = Freshness.test(exp);
        assertTrue(test.isEmpty());
    }

    @Test
    void testNotFresh() {
        Exp exp = ENV.eval("((fn [a b] (fn [a x] a)) (fn [a b] a) (fn [a b] b))", 0);
        Optional<Symbol> test = Freshness.test(exp);
        Assertions.assertEquals(Optional.of(Symbol.of("a")), test);
    }
}