package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.parser.LispParser;
import com.mypack.util.AsSexp;
import com.mypack.vars.Freshness;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class FreshnessTest {

    @Test
    void testFresh() {
        Exp exp = LispParser.parse("((lambda (x12) ((lambda (f11 n2) ((n2 (lambda (x10) (lambda (a5 b5) b5)) " +
                "(lambda (a4 b4) a4)) (lambda (f10 x14) (f10 x14)) (lambda (f4 x11) (n2 ((f11 (lambda (f9 x13) " +
                "((n2 (lambda (g2 h2) (h2 (g2 f9))) (lambda (u4) x13)) (lambda (u5) u5)))) f4) x11)))) x12 x12)) " +
                "(lambda (x4) (lambda (a1 b1) b1)) (lambda (a b) a))");
        Optional<Symbol> test = Freshness.test(exp);
        Assertions.assertTrue(test.isEmpty());
    }

    @Test
    void testSimpleFresh() {
        Exp exp = LispParser.parse("((lambda (a b) b) (lambda (a b) a) (lambda (a b) b))");
        Optional<Symbol> test = Freshness.test(exp);
        Assertions.assertTrue(test.isEmpty());
    }

    @Test
    void testNotFresh() {
        Exp exp = LispParser.parse("((lambda (x12) ((lambda (f11 n2) ((n2 (lambda (x12) (lambda (a5 b5) b5)) " +
                "(lambda (a4 b4) a4)) (lambda (f10 x14) (f10 x14)) (lambda (f4 x11) (n2 ((f11 (lambda (f9 x13) " +
                "((n2 (lambda (g2 h2) (h2 (g2 f9))) (lambda (u4) x13)) (lambda (u5) u5)))) f4) x11)))) x12 x12)) " +
                "(lambda (x4) (lambda (a1 b1) b1)) (lambda (a b) a))");
        Optional<Symbol> test = Freshness.test(exp);
        Assertions.assertEquals(Optional.of(Symbol.of("x12")), test);
    }

    @Test
    void testSimpleNotFresh() {
        Exp exp = LispParser.parse("((lambda (a b) (lambda (a x) a)) (lambda (a b) a) (lambda (a b) b))");
        Optional<Symbol> test = Freshness.test(exp);
        Assertions.assertEquals(Optional.of(Symbol.of("a")), test);
    }

    @Test
    void testSexp() {
        Sexp exp = AsSexp.get(LispParser.parse("((lambda (x12) ((lambda (f11 n2) ((n2 (lambda (x10) (lambda (a5 b5) b5)) " +
                "(lambda (a4 b4) a4)) (lambda (f10 x14) (f10 x14)) (lambda (f4 x11) (n2 ((f11 (lambda (f9 x13) " +
                "((n2 (lambda (g2 h2) (h2 (g2 f9))) (lambda (u4) x13)) (lambda (u5) u5)))) f4) x11)))) x12 x12)) " +
                "(lambda (x4) (lambda (a1 b1) b1)) (lambda (a b) a))"));
        Optional<Symbol> test = Freshness.test(exp);
        Assertions.assertTrue(test.isEmpty());
    }
}