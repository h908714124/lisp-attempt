package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestExtraArgument {

    @Test
    void testExtraArgument() {
        List<Exp> exps = Eval.iterEval(LispParser.parse("((lambda (x) (lambda (n) n)) (lambda (a b) a) (lambda (a b) b))"));
        assertEquals("((lambda (n) n) (lambda (a b) b))", exps.get(1).toString());
        assertEquals("((lambda (a b) b))", exps.get(2).toString());
    }
}
