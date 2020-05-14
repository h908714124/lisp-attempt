package com.mypack.eval;

import com.mypack.exp.Exp;

import java.util.List;
import java.util.function.Function;

public class Lambda {

    public static Function<List<Exp>, Exp> createLambda(List<Exp> lambdaTail) {
        Exp exp = lambdaTail.get(0);
        throw new UnsupportedOperationException();
    }
}
