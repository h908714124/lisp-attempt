package com.mypack.vars;

import com.mypack.builtin.Applicative;
import com.mypack.builtin.HeadSplicing;
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
        List<? extends Exp> exps = sexp.asList();
        List<Exp> parts = null;
        for (int i = 0; i < exps.size(); i++) {
            Exp exp = exps.get(i);
            Exp newExp = exp.accept(this, _null);
            if (newExp != exp) {
                if (parts == null) {
                    parts = new ArrayList<>(exps);
                }
                parts.set(i, newExp);
            }
        }
        if (parts == null) {
            return sexp;
        }
        Sexp newSexp = Sexp.create(parts);
        Exp result = HeadSplicing.simplify(newSexp).orElse(newSexp);
        return Applicative.get().eval(result).orElse(result);
    }

    @Override
    public Exp visitSymbol(Symbol symbol, Void _null) {
        if (this.symbol.equals(symbol)) {
            return value;
        }
        return symbol;
    }

    @Override
    public Exp visitParamBlock(ParamBlock paramBlock) {
        List<Symbol> result = null;
        List<Symbol> symbols = paramBlock.symbols();
        for (int i = 0; i < symbols.size(); i++) {
            Symbol s = symbols.get(i);
            if (symbol.equals(s)) {
                if (result == null) {
                    result = new ArrayList<>(symbols);
                }
                result.set(i, AsSymbol.get(value));
            }
        }
        return result == null ? paramBlock : ParamBlock.create(result);
    }
}
