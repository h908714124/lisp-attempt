package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.vars.AnalysisResult;
import com.mypack.vars.AnalysisVisitor;
import com.mypack.vars.BetaVisitor;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LambdaExpression {

    private static final Pattern SYMBOL_PATTERN = Pattern.compile("([a-z]+)([0-9]+)");
    private final List<Symbol> symbols;
    private final Exp body;

    public LambdaExpression(List<Symbol> symbols, Exp body) {
        this.symbols = symbols;
        this.body = body;
    }

    public Exp apply(Exp arg, Set<Symbol> additionalReserved) {
        AnalysisResult bodyResult = AnalysisVisitor.analyse(body());
        AnalysisResult argResult = AnalysisVisitor.analyse(arg);
        Set<Symbol> intersection = intersection(argResult.bound(), union(bodyResult.bound(), symbols().subList(1, symbols().size())));
        Set<Symbol> union = union(additionalReserved, union(argResult.all(), union(symbols.subList(1, symbols.size()), bodyResult.all())));
        Exp cleanArg = cleanup(arg, intersection, union);
        return BetaVisitor.replace(body, symbols.get(0), cleanArg);
    }

    private static Exp cleanup(Exp arg, Set<Symbol> bound, Set<Symbol> unbound) {
        Set<Symbol> reserved = union(bound, unbound);
        for (Symbol symbol : bound) {
            Map.Entry<Symbol, Exp> entry = removeSymbol(arg, symbol, reserved);
            reserved = union(reserved, List.of(entry.getKey()));
            arg = entry.getValue();
        }
        return arg;
    }

    static Map.Entry<Symbol, Exp> removeSymbol(Exp arg, Symbol symbol, Set<Symbol> union) {
        Symbol alternative = findAlternative(symbol, union);
        return new SimpleImmutableEntry<>(alternative, BetaVisitor.replace(arg, symbol, alternative));
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
        Matcher matcher = SYMBOL_PATTERN.matcher(symbol.value());
        int c = 1;
        if (matcher.matches()) {
            symbol = Symbol.of(matcher.group(1));
        }
        Symbol result;
        do {
            result = Symbol.of(symbol.value() + c++);
        } while (reserved.contains(result));
        return result;
    }

    @Override
    public String toString() {
        return symbols.stream().map(Symbol::toString)
                .collect(Collectors.joining(" ", "(lambda (", ") ")) +
                body + ")";
    }

    public Optional<LambdaExpression> alpha(List<Symbol> newSymbols) {
        if (newSymbols.size() != symbols.size()) {
            return Optional.empty();
        }
        Exp newBody = body;
        for (int i = 0; i < symbols.size(); i++) {
            newBody = BetaVisitor.replace(newBody, symbols.get(i), newSymbols.get(i));
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
