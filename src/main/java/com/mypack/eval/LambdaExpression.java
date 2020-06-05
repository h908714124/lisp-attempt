package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.SetUtil;
import com.mypack.vars.AnalysisResult;
import com.mypack.vars.AnalysisVisitor;
import com.mypack.vars.BetaVisitor;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.mypack.util.SetUtil.union;

public class LambdaExpression {

    private static final Pattern SYMBOL_PATTERN = Pattern.compile("([a-z]+)([0-9]+)");
    private final ParamBlock symbols;
    private final Exp body;

    public LambdaExpression(ParamBlock symbols, Exp body) {
        this.symbols = symbols;
        this.body = body;
    }

    public Exp apply(Exp arg, Set<Symbol> additionalReserved) {
        AnalysisResult bodyResult = AnalysisVisitor.analyse(body());
        AnalysisResult argResult = AnalysisVisitor.analyse(arg);
        Set<Symbol> nonFresh = SetUtil.intersection(argResult.bound(), union(additionalReserved, union(bodyResult.bound(), symbols().tail())));
        Set<Symbol> reserved = union(additionalReserved, union(argResult.all(), union(symbols.tail(), bodyResult.all())));
        Exp cleanArg = cleanup(arg, nonFresh, reserved);
        return BetaVisitor.replace(body, symbols.head(), cleanArg);
    }

    private static Exp cleanup(Exp arg, Set<Symbol> nonFresh, Set<Symbol> reserved) {
        for (Symbol symbol : nonFresh) {
            Map.Entry<Symbol, Exp> entry = removeSymbol(arg, symbol, reserved);
            reserved = union(reserved, List.of(entry.getKey()));
            arg = entry.getValue();
        }
        return arg;
    }

    static Map.Entry<Symbol, Exp> removeSymbol(Exp arg, Symbol symbol, Set<Symbol> reserved) {
        Symbol alternative = findAlternative(symbol, reserved);
        return new SimpleImmutableEntry<>(alternative, BetaVisitor.replace(arg, symbol, alternative));
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
        return symbols.symbols().stream().map(Symbol::toString)
                .collect(Collectors.joining(" ", "(fn [", "] ")) +
                body + ")";
    }

    public Exp toExp() {
        if (symbols.isEmpty()) {
            return body;
        }
        return Sexp.create(Symbol.fn(), symbols, body);
    }

    public ParamBlock symbols() {
        return symbols;
    }

    public Exp body() {
        return body;
    }
}
