package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.AsSexp;
import com.mypack.vars.Vars;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Lambda {

    public static Function<List<Exp>, Exp> createLambda(List<Exp> lambdaTail) {
        Sexp variableList = AsSexp.get(lambdaTail.get(0));
        if (variableList.tail().size() != 1) {
            throw new IllegalArgumentException();
        }
        Vars vars = new Vars(variableList);
        return args -> {
            Exp body = lambdaTail.get(1);
            Map<Symbol, Exp> mapping = vars.createMapping(args);
            throw new IllegalArgumentException();
        };
    }
}
