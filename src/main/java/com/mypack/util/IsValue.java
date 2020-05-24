package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.Value;

public class IsValue {

    public static boolean test(Exp exp) {
        return exp.accept(new TestVisitor() {
            @Override
            public Boolean visitValue(Value value) {
                return true;
            }
        });
    }
}
