package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import com.mypack.vars.AlphaEquivalence;
import org.junit.jupiter.api.Test;

class TestAlphaEquivalence {

    @Test
    void testAlpha() {
        Exp exp = LispParser.parse("(lambda ((lambda (a b1) b1)) (lambda (a b1) b1))");
        boolean eq = AlphaEquivalence.eq("(lambda (a b) b)", exp);
        System.out.println(eq);
    }
}
