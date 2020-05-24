package com.mypack.eval;

import com.mypack.exp.EmptySexp;
import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.exp.Value;

import java.util.ArrayList;
import java.util.Map;

class BetaVisitor implements ExpVisitor<Exp> {

    private final Map<Symbol, Exp> mapping;

    BetaVisitor(Map<Symbol, Exp> mapping) {
        this.mapping = mapping;
    }

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
}
