package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.Symbol;

public class AsSymbol {

    public static Symbol get(Exp exp) {
        return exp.accept(new CastingVisitor<>("symbol expected") {
            @Override
            public Symbol visitSymbol(Symbol symbol) {
                return symbol;
            }
        });
    }
}
