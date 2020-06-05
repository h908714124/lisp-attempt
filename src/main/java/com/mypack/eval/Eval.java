package com.mypack.eval;

import com.mypack.builtin.EvalContext;
import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

public class Eval implements ExpVisitor<Exp, EvalContext> {

    private Eval() {
    }

    private static final Eval INSTANCE = new Eval();

    public static Eval get() {
        return INSTANCE;
    }

    @Override
    public Exp visitSexp(Sexp sexp, EvalContext context) {
        return context.eval(sexp);
    }

    @Override
    public Exp visitSymbol(Symbol symbol) {
        return symbol;
    }

    @Override
    public Exp visitParamBlock(ParamBlock paramBlock) {
        return paramBlock;
    }
}
