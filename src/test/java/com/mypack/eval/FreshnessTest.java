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
    void testNotFresh() {
        Exp exp = LispParser.parse("((lambda (x12) ((lambda (f11 n2) ((n2 (lambda (x10) (lambda (a5 b5) b5)) " +
                "(lambda (a4 b4) a4)) (lambda (f10 x4) (f10 x14)) (lambda (f4 x11) (n2 ((f11 (lambda (f9 x13) " +
                "((n2 (lambda (g2 h2) (h2 (g2 f9))) (lambda (u4) x13)) (lambda (u5) u5)))) f4) x11)))) x12 x12)) " +
                "(lambda (x4) (lambda (a1 b1) b1)) (lambda (a b) a))");
        Optional<Symbol> test = Freshness.test(exp);
        Assertions.assertEquals(Optional.of(Symbol.of("x4")), test);
    }

    @Test
    void testSexp() {
        Sexp exp = AsSexp.get(LispParser.parse("((lambda (x12) ((lambda (f11 n2) ((n2 (lambda (x10) (lambda (a5 b5) b5)) " +
                "(lambda (a4 b4) a4)) (lambda (f10 x14) (f10 x14)) (lambda (f4 x11) (n2 ((f11 (lambda (f9 x13) " +
                "((n2 (lambda (g2 h2) (h2 (g2 f9))) (lambda (u4) x13)) (lambda (u5) u5)))) f4) x11)))) x12 x12)) " +
                "(lambda (x4) (lambda (a1 b1) b1)) (lambda (a b) a))"));
        System.out.println(exp.asList().get(0));
        System.out.println(exp.asList().get(1));
        System.out.println(exp.asList().get(2));
        Optional<Symbol> test = Freshness.test(exp);
        Assertions.assertTrue(test.isEmpty());
    }

    @Test
    void testBadness() {
        Sexp exp = AsSexp.get(LispParser.parse("(((lambda (x) ((lambda (f4 n) ((n (lambda (x4) " +
                "(lambda (a1 b1) b1)) (lambda (a b) a)) (lambda (f3 x3) (f3 x3)) (lambda (f5 x5) " +
                "(n ((f4 (lambda (f2 x6) ((n (lambda (g h) (h (g f2))) (lambda (u) x6)) " +
                "(lambda (u1) u1)))) f5) x5)))) x x)) (lambda (x1) ((lambda (f4 n) " +
                "((n (lambda (x4) (lambda (a1 b1) b1)) (lambda (a b) a)) (lambda (f3 x3) (f3 x3)) " +
                "(lambda (f5 x5) (n ((f4 (lambda (f2 x6) ((n (lambda (g h) (h (g f2))) (lambda (u) x6)) " +
                "(lambda (u1) u1)))) f5) x5)))) x1 x1))) (lambda (f1 x2) (f1 (f1 x2))))"));
        System.out.println(exp.asList().get(0));
        System.out.println(exp.asList().get(1));
        Optional<Symbol> test = Freshness.test(exp);
        System.out.println(test);
        Assertions.assertTrue(test.isEmpty());
    }
}