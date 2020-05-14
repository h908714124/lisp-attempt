package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.Value;

public class AsValue {

    public static Value get(Exp exp) {
        return exp.accept(new DefaultVisitor<>("value expected") {
            @Override
            public Value visitValue(Value value) {
                return value;
            }
        });
    }
}
