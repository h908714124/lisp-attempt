package com.mypack.util;

import com.mypack.builtin.Applicative;
import com.mypack.exp.Exp;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Symbol;

public class DefnExpression {

    private final Symbol name;
    private final ParamBlock params;
    private final Exp body;

    DefnExpression(Symbol name, ParamBlock params, Exp body) {
        this.name = Applicative.checkName(name);
        this.params = params;
        this.body = body;
    }

    public Symbol name() {
        return name;
    }

    public ParamBlock params() {
        return params;
    }

    public Exp body() {
        return body;
    }
}
