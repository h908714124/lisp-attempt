package com.mypack.util;

import com.mypack.builtin.Applicative;
import com.mypack.exp.Exp;
import com.mypack.exp.Symbol;

public class DefExpression {

    private final Symbol name;
    private final Exp definition;

    DefExpression(Symbol name, Exp definition) {
        this.name = Applicative.checkName(name);
        this.definition = definition;
    }

    public Symbol name() {
        return name;
    }

    public Exp definition() {
        return definition;
    }
}
