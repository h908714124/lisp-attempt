package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.AsSexp;
import com.mypack.util.AsSymbol;
import com.mypack.util.ContainsSymbol;
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

    private static LambdaExpression create(Sexp variableList, Exp body) {
        return new LambdaExpression(createSymbols(variableList), body);
    }

    static LambdaExpression create(Exp exp) {
        List<? extends Exp> lambdaTail = AsSexp.get(exp).tail();
        if (lambdaTail.size() != 2) {
            throw new IllegalArgumentException("Invalid lambda expression: " + exp);
        }
        Sexp variableList = AsSexp.get(lambdaTail.get(0));
        Exp lambdaBody = lambdaTail.get(1);
        return create(variableList, lambdaBody);
    }

    Exp betaReduction(List<? extends Exp> args) {
        List<Exp> newArgs = new ArrayList<>(args.size());
        for (Exp arg : args) {
            newArgs.add(cleanup(arg));
        }
        return doBeta(newArgs);
    }

    private Exp cleanup(Exp arg) {
        for (Symbol symbol : symbols) {
            if (ContainsSymbol.test(symbol, arg)) {
                Symbol alternative = findAlternative(symbol);
                arg = arg.accept(new BetaVisitor(Collections.singletonMap(symbol, alternative), Collections.emptyList()));
            }
        }
        return arg;
    }

    private Symbol findAlternative(Symbol symbol) {
        int current = 1;
        Symbol result;
        do {
            result = Symbol.of(symbol.value() + current++);
        } while (symbols.contains(result));
        return result;
    }

    private Exp doBeta(List<? extends Exp> args) {
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
