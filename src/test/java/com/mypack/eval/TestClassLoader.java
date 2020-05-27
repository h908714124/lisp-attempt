package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.parser.LispParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class TestClassLoader {

    @Test
    void testLisp() throws IOException {
        String data = Files.readString(Paths.get("src/lisp/fact.lisp"));
        List<Exp> expressions = LispParser.parseList(data);
        Assertions.assertEquals(2, expressions.size());
    }
}
