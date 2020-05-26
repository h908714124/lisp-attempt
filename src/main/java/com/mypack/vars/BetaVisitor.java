package com.mypack.vars;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BetaVisitor implements ExpVisitor<Exp> {

    private final Map<Symbol, Exp> mapping;

    private final List<Symbol> remainingSymbols;

    public BetaVisitor(Map<Symbol, Exp> mapping, List<Symbol> remainingSymbols) {
        this.mapping = mapping;
        this.remainingSymbols = remainingSymbols;
    }

    @Override
    public Exp visitSexp(Sexp sexp) {
        Exp newHead = sexp.head().accept(this);
        List<Exp> newTail = new ArrayList<>();
        for (Exp exp : sexp.tail()) {
            newTail.add(exp.accept(this));
        }
        return new Sexp(newHead, newTail);
    }

    @Override
    public Exp visitSymbol(Symbol symbol) {
        return mapping.getOrDefault(symbol, symbol);
    }

    public List<Symbol> remainingSymbols() {
        return remainingSymbols;
    }
}
