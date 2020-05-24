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
        Vars vars = new Vars(variableList);
        Exp body = lambdaTail.get(1);
        return args -> {
            if (args.isEmpty()) {
                return new Sexp(Symbol.lambda(), lambdaTail);
            }
            Map<Symbol, Exp> mapping = vars.createMapping(args);
            return body.accept(new BetaVisitor(mapping));
        };
    }

}
