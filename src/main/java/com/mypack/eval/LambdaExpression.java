package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.AsSexp;
import com.mypack.util.AsSymbol;
import com.mypack.util.ContainsSymbol;
import com.mypack.vars.AnalysisResult;
import com.mypack.vars.AnalysisVisitor;
import com.mypack.vars.BetaVisitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class LambdaExpression {

    private final List<Symbol> symbols;
    private final Exp body;

    LambdaExpression(List<Symbol> symbols, Exp body) {
        if (new HashSet<>(symbols).size() < symbols.size()) {
            throw new IllegalArgumentException("Symbols are not unique: " + symbols);
        }
        this.symbols = symbols;
        this.body = body;
    }

    private static LambdaExpression create(Sexp variableList, Exp body) {
        return new LambdaExpression(createSymbols(variableList), body);
    }

    public static LambdaExpression create(Exp exp) {
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
        AnalysisResult bodyResult = AnalysisVisitor.analyse(body);
        Set<Symbol> bound = new HashSet<>(bodyResult.getBound());
        bound.addAll(symbols);
        for (Exp arg : args) {
            newArgs.add(cleanup(arg, bound, bodyResult.getUnbound()));
        }
        return doBeta(newArgs);
    }

    private static Exp cleanup(Exp arg, Set<Symbol> bound, Set<Symbol> unbound) {
        for (Symbol boundSymbol : bound) {
            if (ContainsSymbol.test(boundSymbol, arg)) {
                HashSet<Symbol> bound2 = new HashSet<>(bound);
                HashSet<Symbol> unbound2 = new HashSet<>(unbound);
                AnalysisResult argResult = AnalysisVisitor.analyse(arg);
                bound2.addAll(argResult.getBound());
                unbound2.addAll(argResult.getUnbound());
                Symbol alternative = findAlternative(boundSymbol, bound2, unbound2);
                arg = arg.accept(new BetaVisitor(Collections.singletonMap(boundSymbol, alternative), Collections.emptyList()));
            }
        }
        return arg;
    }

    private static Symbol findAlternative(Symbol symbol, Set<Symbol> bound, Set<Symbol> unbound) {
        int current = 1;
        Symbol result;
        do {
            result = Symbol.of(symbol.value() + current++);
        } while (bound.contains(result) || unbound.contains(result));
        return result;
    }

    private Exp doBeta(List<? extends Exp> args) {
        BetaVisitor visitor = createBeta(args);
        Exp result = body.accept(visitor);
        if (visitor.remainingSymbols().isEmpty()) {
            return result;
        } else { // partial application
            return Sexp.create(Arrays.asList(Symbol.lambda(), Sexp.create(visitor.remainingSymbols()), result));
        }
    }

    private static List<Symbol> createSymbols(Sexp variableList) {
        return variableList.asList().stream()
                .map(AsSymbol::get)
                .collect(Collectors.toList());
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

    public Optional<LambdaExpression> alpha(List<Symbol> newSymbols) {
        if (newSymbols.size() != symbols.size()) {
            return Optional.empty();
        }
        Map<Symbol, Exp> map = new HashMap<>();
        for (int i = 0; i < symbols.size(); i++) {
            Symbol symbol = symbols.get(i);
            Symbol newSymbol = newSymbols.get(i);
            map.put(symbol, newSymbol);
        }
        Exp newBody = body.accept(new BetaVisitor(map, Collections.emptyList()));
        return Optional.of(new LambdaExpression(newSymbols, newBody));
    }

    public Exp toExp() {
        return Sexp.create(Arrays.asList(Symbol.lambda(), Sexp.create(symbols), body));
    }

    public List<Symbol> symbols() {
        return symbols;
    }

    public Exp body() {
        return body;
    }
}
