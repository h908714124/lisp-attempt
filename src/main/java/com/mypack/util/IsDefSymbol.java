package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.Symbol;

class IsDefSymbol {

    private static final IsSpecificSymbolVisitor INSTANCE = new IsSpecificSymbolVisitor(Symbol.of("def"));

    static boolean test(Exp exp) {
        return exp.accept(INSTANCE);
    }
}
