package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.vars.AnalysisResult;
import com.mypack.vars.AnalysisVisitor;
import com.mypack.vars.BetaVisitor;
import com.mypack.vars.Boundness;
import com.mypack.vars.Freshness;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LambdaExpression {

    private final List<Symbol> symbols;
    private final Exp body;

    private static AtomicInteger current = new AtomicInteger(1);

    public LambdaExpression(List<Symbol> symbols, Exp body) {
        if (new HashSet<>(symbols).size() < symbols.size()) {
            throw new IllegalArgumentException("Symbols are not unique: " + symbols);
        }
        this.symbols = symbols;
        this.body = body;
    }

    Exp apply(Exp arg) {
        AnalysisResult bodyResult = AnalysisVisitor.analyse(body);
        Set<Symbol> bound = new HashSet<>(bodyResult.bound());
        bound.addAll(symbols.subList(1, symbols.size()));
        Exp cleanArg = cleanup(arg, bound, bodyResult.unbound());
        return doBeta(cleanArg);
    }

    private Exp doBeta(Exp arg) {
        Freshness.test(arg).ifPresent(symbol -> {
            throw new IllegalStateException("Non-fresh pre: " + symbol);
        });
        BetaVisitor visitor = new BetaVisitor(symbols.get(0), arg);
        Exp result = body.accept(visitor);
        Optional<Symbol> nonFreshSymbol = Freshness.test(result);
        if (nonFreshSymbol.isPresent()) {
            throw new IllegalStateException("Non-fresh post: " + nonFreshSymbol.get());
        }
        return result;
    }

    private static Exp cleanup(Exp arg, Set<Symbol> bound, Set<Symbol> unbound) {
        Set<Symbol> reserved = union(bound, unbound);
        for (Symbol symbol : bound) {
            arg = removeSymbol(arg, symbol, reserved);
        }
        return arg;
    }

    static Exp removeSymbol(Exp arg, Symbol symbol, Set<Symbol> reserved) {
        if (Boundness.test(arg, symbol)) {
            AnalysisResult argResult = AnalysisVisitor.analyse(arg);
            Set<Symbol> reservedArg = union(argResult.bound(), argResult.unbound());
            Symbol alternative = findAlternative(symbol, union(reserved, reservedArg));
            arg = arg.accept(new BetaVisitor(symbol, alternative));
        }
        if (Boundness.test(arg, symbol)) { // remove this later
            throw new AssertionError();
        }
        return arg;
    }

    public static Set<Symbol> union(Collection<Symbol> a, Collection<Symbol> b) {
        Set<Symbol> result = new LinkedHashSet<>(a.size() + b.size());
        result.addAll(a);
        result.addAll(b);
        return result;
    }

    private static Set<Symbol> intersection(Set<Symbol> a, Set<Symbol> b) {
        Set<Symbol> result = new LinkedHashSet<>();
        for (Symbol as : a) {
            if (b.contains(as)) {
                result.add(as);
            }
        }
        return result;
    }

    static Symbol findAlternative(Symbol symbol, Set<Symbol> reserved) {
        Matcher matcher = Pattern.compile("([a-z]+)([0-9]+)").matcher(symbol.value());
        if (matcher.matches()) {
            symbol = Symbol.of(matcher.group(1));
        }
        Symbol result;
        do {
            result = Symbol.of(symbol.value() + current.getAndIncrement());
        } while (reserved.contains(result));
        return result;
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
        Exp newBody = body;
        for (int i = 0; i < symbols.size(); i++) {
            newBody = newBody.accept(new BetaVisitor(symbols.get(i), newSymbols.get(i)));
        }
        return Optional.of(new LambdaExpression(newSymbols, newBody));
    }

    public Exp toExp() {
        if (symbols.isEmpty()) {
            return body;
        }
        return Sexp.create(Arrays.asList(Symbol.lambda(), Sexp.create(symbols), body));
    }

    public List<Symbol> symbols() {
        return symbols;
    }

    public Exp body() {
        return body;
    }
}
