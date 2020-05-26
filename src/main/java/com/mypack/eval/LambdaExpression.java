package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.AsSymbol;
import com.mypack.vars.BetaVisitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class LambdaExpression {

    private final List<Symbol> symbols;
    private final Exp body;

    LambdaExpression(List<Symbol> symbols, Exp body) {
        this.symbols = symbols;
        this.body = body;
    }

    static LambdaExpression create(Sexp variableList, Exp body) {
        return new LambdaExpression(createSymbols(variableList), body);
    }

    Exp betaReduction(List<? extends Exp> args) {
        BetaVisitor visitor = createBeta(args);
        Exp result = body.accept(visitor);
        if (visitor.remainingSymbols().isEmpty()) {
            return result;
        } else { // partial application
            return new Sexp(Symbol.lambda(), Arrays.asList(Sexp.createArgumentList(visitor.remainingSymbols()), result));
        }
    }

    LambdaExpression alphaConversion(Symbol a, Symbol b) {
        Exp newBody = body.accept(new BetaVisitor(Collections.singletonMap(a, b), Collections.emptyList()));
        List<Symbol> newSymbols = new ArrayList<>(symbols.size());
        for (Symbol symbol : symbols) {
            if (a.equals(symbol)) {
                newSymbols.add(b);
            } else {
                newSymbols.add(symbol);
            }
        }
        return new LambdaExpression(newSymbols, newBody);
    }

    private static List<Symbol> createSymbols(Sexp variableList) {
        List<Symbol> symbols = new ArrayList<>();
        symbols.add(AsSymbol.get(variableList.head()));
        for (Exp exp : variableList.tail()) {
            symbols.add(AsSymbol.get(exp));
        }
        return symbols;
    }

    private BetaVisitor createBeta(List<? extends Exp> args) {
        if (args.size() > symbols.size()) {
            throw new IllegalArgumentException("Expecting " + symbols.size() + " arguments but found " + args.toString());
        }
        Map<Symbol, Exp> result = new HashMap<>(symbols.size());
        for (int i = 0; i < args.size(); i++) {
            result.put(symbols.get(i), args.get(i));
        }
        List<Symbol> remainingSymbols = symbols.subList(args.size(), symbols.size());
        return new BetaVisitor(result, remainingSymbols);
    }

    @Override
    public String toString() {
        return symbols.stream().map(Symbol::toString)
                .collect(Collectors.joining(", ", "(lambda (", ") ")) +
                body + ")";
    }
}
