package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import com.mypack.util.AsValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

class ExtractNumberTest {

    @Test
    void testEvalPlus() {
        Exp exp = LispParser.parse("(+ 4 5)");
        Exp result = exp.accept(new Eval());
        Assertions.assertEquals(BigInteger.valueOf(9), AsValue.get(result).value());
    }

    @Test
    void testEvalPlusMore() {
        Exp exp = LispParser.parse("(+ (+ 1 ()) (+ () 2 (+ 3 4 (+ 5 6 (+ 7 8 9)))))");
        Exp result = exp.accept(new Eval());
        Assertions.assertEquals(BigInteger.valueOf(45), AsValue.get(result).value());
    }

    @Test
    void testEvalMixed() {
        Exp exp = LispParser.parse("(* (+ 1 2) (* 1 3))");
        Exp result = exp.accept(new Eval());
        Assertions.assertEquals(BigInteger.valueOf(9), AsValue.get(result).value());
    }
}