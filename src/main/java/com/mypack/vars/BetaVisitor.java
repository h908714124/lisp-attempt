package com.mypack.vars;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.AsSymbol;

import java.util.ArrayList;
import java.util.List;

public class BetaVisitor implements ExpVisitor<Exp, Void> {

    private final Symbol symbol;
    private final Exp value;

    private BetaVisitor(Symbol symbol, Exp value) {
        this.symbol = symbol;
        this.value = value;
    }

    /**
     * Replaces all occurrences of the symbol, with no
     * regard to the symbol's position.
     */
    public static Exp replace(Exp exp, Symbol symbol, Exp value) {
        return exp.accept(new BetaVisitor(symbol, value), null);
    }

    @Override
    public Exp visitSexp(Sexp sexp, Void _null) {
        List<Exp> result = new ArrayList<>(sexp.size());
        for (Exp exp : sexp.asList()) {
            result.add(exp.accept(this, _null));
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

    @Override
    public Exp visitParamBlock(ParamBlock paramBlock) {
        List<Symbol> result = new ArrayList<>(paramBlock.size());
        for (Symbol s : paramBlock.symbols()) {
            if (symbol.equals(s)) {
                result.add(AsSymbol.get(value));
            } else {
                result.add(s);
            }
        }
        return ParamBlock.create(result);
    }
}
