package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.Symbol;

class IsLambdaSymbol {

    private static final IsSpecificSymbolVisitor INSTANCE = new IsSpecificSymbolVisitor(Symbol.lambda());

    static boolean test(Exp exp) {
        return exp.accept(INSTANCE);
    }
}
