package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

class ExtractFunctionTest {


    @Test
    void testSomething() {
        Exp exp = LispParser.parse("((lambda (x) ((lambda (a b) a) x x)))");
        Function<List<Exp>, Exp> result = exp.accept(new ExtractFunction());
        Assertions.assertEquals("(+ 1 2)", result.toString());
    }
}