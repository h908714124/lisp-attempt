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

    @Test
    void testBadness() {
        Sexp exp = AsSexp.get(LispParser.parse("(((lambda (f4 n) " +
                "((n (lambda (x4) (lambda (a1 b1) b1)) (lambda (a b) a)) (lambda (f3 x3) (f3 x3)) " +
                "(lambda (f5 x5) (n ((f4 (lambda (f2 x2) ((n (lambda (g h) (h (g f2))) (lambda (u) x2)) " +
                "(lambda (u1) u1)))) f5) x5)))) (lambda (x) ((lambda (f7 n1) ((n1 (lambda (x7) " +
                "(lambda (a3 b3) b3)) (lambda (a2 b2) a2)) (lambda (f6 x6) (f6 x6)) (lambda (f8 x8) " +
                "(n1 ((f7 (lambda (f1 x1) ((n1 (lambda (g1 h1) (h1 (g1 f1))) (lambda (u2) x1)) " +
                "(lambda (u3) u3)))) f8) x8)))) x x)) (lambda (x) ((lambda (f7 n1) " +
                "((n1 (lambda (x7) (lambda (a3 b3) b3)) (lambda (a2 b2) a2)) (lambda (f6 x6) (f6 x6)) " +
                "(lambda (f8 x8) (n1 ((f7 (lambda (f1 x1) ((n1 (lambda (g1 h1) (h1 (g1 f1))) " +
                "(lambda (u2) x1)) (lambda (u3) u3)))) f8) x8)))) x x))) (lambda (f1 x1) (f1 (f1 x1))))"));
        Optional<Symbol> test = Freshness.test(exp);
        Assertions.assertTrue(test.isEmpty());
    }
}