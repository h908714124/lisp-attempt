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
import java.util.LinkedHashSet;
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
        Set<Symbol> bound = new HashSet<>(bodyResult.bound());
        bound.addAll(symbols);
        for (Exp arg : args) {
            newArgs.add(cleanup(arg, bound, bodyResult.unbound()));
        }
        return doBeta(newArgs);
    }

    private static Exp cleanup(Exp arg, Set<Symbol> bound, Set<Symbol> unbound) {
        Set<Symbol> reserved = union(bound, unbound);
        for (Symbol symbol : bound) {
            arg = removeSymbol(arg, symbol, reserved);
        }
        return arg;
    }

    static Exp removeSymbol(Exp arg, Symbol symbol, Set<Symbol> reserved) {
        if (ContainsSymbol.test(symbol, arg)) {
            AnalysisResult argResult = AnalysisVisitor.analyse(arg);
            Set<Symbol> reservedArg = union(argResult.bound(), argResult.unbound());
            Symbol alternative = findAlternative(symbol, union(reserved, reservedArg));
            arg = arg.accept(new BetaVisitor(Collections.singletonMap(symbol, alternative), Collections.emptyList()));
        }
        if (ContainsSymbol.test(symbol, arg)) { // remove this later
            throw new AssertionError();
        }
        return arg;
    }

    static Set<Symbol> union(Set<Symbol> a, Set<Symbol> b) {
        Set<Symbol> result = new LinkedHashSet<>(a.size() + b.size());
        result.addAll(a);
        result.addAll(b);
        return result;
    }

    private static Symbol findAlternative(Symbol symbol, Set<Symbol> reserved) {
        int current = 1;
        Symbol result;
        do {
            result = Symbol.of(symbol.value() + current++);
        } while (reserved.contains(result));
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
