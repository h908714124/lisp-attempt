package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.Symbol;

public class IsLambda {

    public static boolean test(Exp exp) {
        return exp.accept(new TestVisitor() {
            @Override
            public Boolean visitSymbol(Symbol symbol) {
                return symbol.value().equals("lambda");
            }
        });
    }
}
