package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import com.mypack.util.AsValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

class LambdaTest {

    @Test
    void testChurchTrue() {
        Exp exp = LispParser.parse("((lambda (a b) a) 1 2)");
        Exp result = exp.accept(new Eval());
        Assertions.assertEquals(BigInteger.valueOf(1), AsValue.get(result).value());
    }

    @Test
    void testChurchFalse() {
        Exp exp = LispParser.parse("((lambda (a b) b) (+ 1 1) (+ 1 2))");
        Exp result = exp.accept(new Eval());
        Assertions.assertEquals("(+ 1 2)", result.toString());
    }
}