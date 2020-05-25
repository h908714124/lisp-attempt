package com.mypack.vars;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import java.util.ArrayList;
import java.util.Map;

public class Mapping implements ExpVisitor<Exp> {

    private final Map<Symbol, Exp> mapping;

    public Mapping(Map<Symbol, Exp> mapping) {
        this.mapping = mapping;
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
    public Exp visitSymbol(Symbol symbol) {
        return mapping.getOrDefault(symbol, symbol);
    }
}
