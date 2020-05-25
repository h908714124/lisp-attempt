package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.AsSymbol;
import com.mypack.vars.Mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Apply {

    static Exp betaReduction(Sexp variableList, Exp body, List<Exp> args) {
        Mapping mapping = createMapping(variableList, args);
        return body.accept(mapping);
    }

    private static List<Symbol> createSymbols(Sexp variableList) {
        List<Symbol> symbols = new ArrayList<>();
        symbols.add(AsSymbol.get(variableList.head()));
        for (Exp exp : variableList.tail()) {
            symbols.add(AsSymbol.get(exp));
        }
        return symbols;
    }

    private static Mapping createMapping(Sexp variableList, List<Exp> args) {
        List<Symbol> symbols = createSymbols(variableList);
        if (args.size() != symbols.size()) {
            throw new IllegalArgumentException();
        }
        Map<Symbol, Exp> result = new HashMap<>(symbols.size());
        for (int i = 0; i < symbols.size(); i++) {
            result.put(symbols.get(i), args.get(i));
        }
        return new Mapping(result);
    }
}
