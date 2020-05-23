package com.mypack.eval;

import com.mypack.exp.EmptySexp;
import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.exp.Value;
import com.mypack.util.AsSexp;
import com.mypack.vars.Vars;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Lambda {

    public static Function<List<Exp>, Exp> createLambda(List<Exp> lambdaTail) {
        Sexp variableList = AsSexp.get(lambdaTail.get(0));
        Vars vars = new Vars(variableList);
        Exp body = lambdaTail.get(1);
        return args -> {
            Map<Symbol, Exp> mapping = vars.createMapping(args);
            return body.accept(new ExpVisitor<Exp>() {
                @Override
                public Exp visitEmptySexp(EmptySexp emptySexp) {
                    return emptySexp;
                }

                @Override
                public Exp visitSexp(Sexp sexp) {
                    Exp newHead = sexp.head().accept(this);
                    ArrayList<Exp> newTail = new ArrayList<>();
                    for (Exp exp : sexp.tail()) {
                        newTail.add(exp.accept(this));
                    }
                    return new Sexp(newHead, newTail);
                }

                @Override
                public Exp visitValue(Value value) {
                    return value;
                }

                @Override
                public Exp visitSymbol(Symbol symbol) {
                    return mapping.getOrDefault(symbol, symbol);
                }
            });
        };
    }
}
