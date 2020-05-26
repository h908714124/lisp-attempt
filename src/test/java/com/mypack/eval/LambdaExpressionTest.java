package com.mypack.eval;

import com.mypack.exp.Symbol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class LambdaExpressionTest {

    @Test
    void testAlphaConversion() {
        LambdaExpression lambda = new LambdaExpression(Arrays.asList(Symbol.of("a"), Symbol.of("b")), Symbol.of("a"));
        LambdaExpression converted = lambda.alphaConversion(Symbol.of("a"), Symbol.of("c"));
        Assertions.assertEquals("(lambda (c, b) c)", converted.toString());
    }
}