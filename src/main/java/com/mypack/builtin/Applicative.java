package com.mypack.builtin;

import com.mypack.exp.Exp;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.AsSexp;
import com.mypack.util.AsSymbol;
import com.mypack.util.IsSexp;
import com.mypack.util.IsSymbol;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;

import static com.mypack.eval.Environment.nestedInvocations;

public class Applicative {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("0|[-]?[1-9]\\d*");

    private static final Applicative INSTANCE = new Applicative();

    private final Map<Symbol, Function<Sexp, Optional<Exp>>> map;

    public static Applicative get() {
        return INSTANCE;
    }

    public static Symbol checkName(Symbol symbol) {
        if (symbol.equals(Symbol.of("Y"))
                || NUMBER_PATTERN.matcher(symbol.value()).matches()
                || INSTANCE.map.containsKey(symbol)) {
            throw new IllegalArgumentException("Reserved: " + symbol);
        }
        return symbol;
    }

    private Applicative() {
        Map<Symbol, Function<Sexp, Optional<Exp>>> m = new HashMap<>();
        m.put(Symbol.of("pred"), (sexp -> {
            if (sexp.size() < 2) {
                return Optional.empty();
            }
            return tryEval(sexp.get(1)).flatMap(i -> {
                i = i.subtract(BigInteger.ONE);
                if (i.signum() == -1) {
                    return Optional.<Exp>empty(); // no negative numbers
                }
                return Optional.of(Symbol.of(i.toString()));
            });
        }));
        m.put(Symbol.of("zero?"), (sexp -> {
            if (sexp.size() < 4) {
                return Optional.empty();
            }
            return tryEval(sexp.get(1)).map(i -> {
                Exp newHead = i.equals(BigInteger.ZERO) ? sexp.get(2) : sexp.get(3);
                return HeadSplicing.trySplicing(Sexp.create(newHead, sexp.subList(4))).orElse(newHead);
            });
        }));
        m.put(Symbol.of("*"), (sexp -> {
            BigInteger current = BigInteger.ONE;
            for (Exp exp : sexp.tail()) {
                Optional<BigInteger> e = tryEval(exp);
                if (e.isEmpty()) {
                    return Optional.empty();
                }
                current = current.multiply(e.get());
            }
            return Optional.of(Symbol.of(current.toString()));
        }));
        m.put(Symbol.of("+"), (sexp -> {
            BigInteger current = BigInteger.ZERO;
            for (Exp exp : sexp.tail()) {
                Optional<BigInteger> e = tryEval(exp);
                if (e.isEmpty()) {
                    return Optional.empty();
                }
                current = current.add(e.get());
            }
            return Optional.of(Symbol.of(current.toString()));
        }));
        m.put(Symbol.of("-"), (sexp -> {
            if (sexp.tail().size() < 2) {
                return Optional.empty();
            }
            Exp q = sexp.tail().get(0);
            Optional<BigInteger> p = tryEval(q);
            return p.flatMap(mn -> {
                Exp exp = sexp.tail().get(1);
                return tryEval(exp).flatMap(s -> {
                    BigInteger current = mn.subtract(s);
                    if (current.signum() == -1) {
                        return Optional.<Exp>empty(); // no negative numbers yet
                    }
                    Symbol r = Symbol.of(current.toString());
                    if (sexp.tail().size() == 2) {
                        return Optional.of(r);
                    }
                    return Optional.of(Sexp.create(r, sexp.tail(2)));
                });
            });
        }));
        m.put(Symbol.of("false"), (sexp -> {
            if (sexp.size() < 3) {
                return Optional.empty();
            }
            if (sexp.size() == 3) {
                return Optional.of(sexp.get(2));
            }
            return Optional.of(Sexp.create(sexp.get(2), sexp.subList(3)));
        }));
        m.put(Symbol.of("true"), (sexp -> {
            if (sexp.size() < 3) {
                return Optional.empty();
            }
            if (sexp.size() == 3) {
                return Optional.of(sexp.get(1));
            }
            return Optional.of(Sexp.create(sexp.get(1), sexp.subList(3)));
        }));
        this.map = Map.copyOf(m);
    }

    Optional<BigInteger> tryEval(Exp exp) {
        return eval(exp)
                .filter(x -> IsSymbol.test(x, NUMBER_PATTERN))
                .map(x -> new BigInteger(AsSymbol.get(x).value()));
    }

    public Optional<Exp> eval(Exp exp) {
        if (IsSymbol.test(exp, NUMBER_PATTERN)) {
            return Optional.of(exp);
        }
        return IsSexp.test(exp) ? eval(AsSexp.get(exp)) : Optional.empty();
    }

    Optional<Exp> eval(Sexp sexp) {
        if (!IsSymbol.test(sexp.head())) {
            return Optional.empty();
        }
        Symbol symbol = AsSymbol.get(sexp.head());
        if (NUMBER_PATTERN.matcher(symbol.value()).matches()) {
            return applyNumberBuiltIn(sexp);
        }
        Function<Sexp, Optional<Exp>> builtin = map.get(symbol);
        if (builtin == null) {
            return Optional.empty();
        }
        return builtin.apply(sexp);
    }

    private static Optional<Exp> applyNumberBuiltIn(Sexp sexp) {
        if (sexp.size() < 3) {
            return Optional.empty();
        }
        Exp invocations = nestedInvocations(Integer.parseInt(AsSymbol.get(sexp.head()).value()),
                sexp.get(1), sexp.get(2));
        return Optional.of(HeadSplicing.trySplicing(Sexp.create(invocations, sexp.subList(3))).orElse(invocations));
    }
}
