package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.vars.Freshness;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestBadStuff {

    private final Environment env = new Environment();

    @BeforeAll
    void setUp() {
        env.load(Path.of("src/clj/fact.clj"));
    }

    @Test
    void name() {
        Exp freshExp = env.eval("(fn [f x] (((* (pred 3) ((Y fact_) (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x))))", 0);
        Exp exp = env.eval(freshExp, 1);
        Freshness.test(freshExp).ifPresent(nonFreshSymbol ->
                fail("non fresh before eval: " + nonFreshSymbol));
        Freshness.test(exp).ifPresent(nonFreshSymbol ->
                fail("non fresh after eval: " + nonFreshSymbol));
    }
}
