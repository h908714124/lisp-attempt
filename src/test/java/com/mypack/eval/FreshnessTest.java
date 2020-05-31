package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.Symbol;
import com.mypack.parser.LispParser;
import com.mypack.vars.Freshness;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class FreshnessTest {

    @Test
    void testFresh() {
        Exp exp = LispParser.parse("((lambda [a b] b) (lambda [a b] a) (lambda [a b] b))");
        Optional<Symbol> test = Freshness.test(exp);
        Assertions.assertTrue(test.isEmpty());
    }

    @Test
    void testNotFresh() {
        Exp exp = LispParser.parse("((lambda [a b] (lambda [a x] a)) (lambda [a b] a) (lambda [a b] b))");
        Optional<Symbol> test = Freshness.test(exp);
        Assertions.assertEquals(Optional.of(Symbol.of("a")), test);
    }
}