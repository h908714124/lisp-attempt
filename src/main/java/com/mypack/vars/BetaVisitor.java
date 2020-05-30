package com.mypack.vars;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import java.util.ArrayList;
import java.util.List;

public class BetaVisitor implements ExpVisitor<Exp> {

    private final Symbol symbol;
    private final Exp value;

    private BetaVisitor(Symbol symbol, Exp value) {
        this.symbol = symbol;
        this.value = value;
    }

    /**
     * Replaces all occurrences of the symbol, with no
     * regard to the symbol's position.
     * For lambda calculus purposes, the symbol should not occur in
     * a parameter list anywhere in the expression.
     */
    public static Exp replace(Exp exp, Symbol symbol, Exp value) {
        return exp.accept(new BetaVisitor(symbol, value));
    }

    @Override
    public Exp visitSexp(Sexp sexp) {
        List<Exp> result = new ArrayList<>(sexp.size());
        for (Exp exp : sexp.asList()) {
            result.add(exp.accept(this));
        }
        return Sexp.create(result);
    }

    @Override
    public Exp visitSymbol(Symbol symbol) {
        if (this.symbol.equals(symbol)) {
            return value;
        }
        return symbol;
    }
}
