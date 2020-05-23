package com.mypack.vars;

import com.mypack.exp.Exp;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.AsSymbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vars {

    private final List<Symbol> symbols;

    public Vars(Sexp variableList) {
        this.symbols = new ArrayList<>();
        this.symbols.add(AsSymbol.get(variableList.head()));
        for (Exp exp : variableList.tail()) {
            this.symbols.add(AsSymbol.get(exp));
        }
    }

    public Map<Symbol, Exp> createMapping(List<Exp> args) {
        if (args.size() != symbols.size()) {
            throw new IllegalArgumentException();
        }
        Map<Symbol, Exp> result = new HashMap<>(symbols.size());
        for (int i = 0; i < symbols.size(); i++) {
            result.put(symbols.get(i), args.get(i));
        }
        return result;
    }
}
