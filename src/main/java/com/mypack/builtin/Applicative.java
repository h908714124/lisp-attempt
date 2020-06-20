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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Applicative {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("0|[1-9]\\d*");

    private static final Applicative INSTANCE = new Applicative();

    private final Map<Symbol, Function<List<? extends Exp>, Optional<Exp>>> map;

    public static Applicative get() {
        return INSTANCE;
    }

    public static Symbol checkName(Symbol symbol) {
        if (symbol.equals(Symbol.of("Y"))
                || NUMBER_PATTERN.matcher(symbol.value()).matches()
                || INSTANCE.map.containsKey(symbol)) {
            throw new IllegalArgumentException("This symbol is reserved: " + symbol);
        }
        return symbol;
    }

    private Applicative() {
        Map<Symbol, Function<List<? extends Exp>, Optional<Exp>>> m = new HashMap<>();
        m.put(Symbol.of("pred"), (tail -> {
            if (tail.isEmpty()) {
                return Optional.empty();
            }
            return evalNumber(tail.get(0)).flatMap(i -> {
                i = i.subtract(BigInteger.ONE);
                if (i.signum() == -1) {
                    return Optional.<Exp>empty(); // no negative numbers
                }
                return Optional.of(Symbol.of(i.toString()));
            });
        }));
        m.put(Symbol.of("zero?"), (tail -> {
            if (tail.size() < 3) {
                return Optional.empty();
            }
            return evalNumber(tail.get(0)).flatMap(i -> {
                Exp newHead = i.equals(BigInteger.ZERO) ? tail.get(1) : tail.get(2);
                return HeadSplicing.assemble(newHead, tail.subList(3, tail.size()));
            });
        }));
        m.put(Symbol.of("*"), (tail -> {
            BigInteger current = BigInteger.ONE;
            for (Exp exp : tail) {
                Optional<BigInteger> e = evalNumber(exp);
                if (e.isEmpty()) {
                    return Optional.empty();
                }
                current = current.multiply(e.get());
            }
            return Optional.of(Symbol.of(current.toString()));
        }));
        m.put(Symbol.of("+"), (tail -> {
            BigInteger current = BigInteger.ZERO;
            for (Exp exp : tail) {
                Optional<BigInteger> e = evalNumber(exp);
                if (e.isEmpty()) {
                    return Optional.empty();
                }
                current = current.add(e.get());
            }
            return Optional.of(Symbol.of(current.toString()));
        }));
        m.put(Symbol.of("-"), (tail -> {
            if (tail.size() < 2) {
                return Optional.empty();
            }
            return evalNumber(tail.get(0)).flatMap(mn -> {
                Exp exp = tail.get(1);
                return evalNumber(exp).flatMap(s -> {
                    BigInteger r = mn.subtract(s);
                    if (r.signum() == -1) {
                        return Optional.<Exp>empty(); // no negative numbers yet
                    }
                    return HeadSplicing.assemble(Symbol.of(r.toString()), tail.subList(2, tail.size()));
                });
            });
        }));
        m.put(Symbol.of("false"), (tail -> {
            if (tail.size() < 2) {
                return Optional.empty();
            }
            return HeadSplicing.assemble(tail.get(1), tail.subList(2, tail.size()));
        }));
        m.put(Symbol.of("true"), (tail -> {
            if (tail.size() < 2) {
                return Optional.empty();
            }
            return HeadSplicing.assemble(tail.get(0), tail.subList(2, tail.size()));
        }));
        this.map = Map.copyOf(m);
    }

    Optional<BigInteger> evalNumber(Exp exp) {
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
            return applyNumberBuiltIn(new BigInteger(symbol.value()), sexp.tail());
        }
        Function<List<? extends Exp>, Optional<Exp>> builtin = map.get(symbol);
        if (builtin == null) {
            return Optional.empty();
        }
        return builtin.apply(sexp.tail());
    }

    private Optional<Exp> applyNumberBuiltIn(BigInteger m, List<? extends Exp> tail) {
        if (tail.isEmpty()) {
            return Optional.empty();
        }
        Optional<BigInteger> _n_ = evalNumber(tail.get(0));
        if (tail.size() >= 2
                && IsSymbol.test(tail.get(0))
                && _n_.isEmpty()) {
            Exp hewHead = nestedInvocations(m.intValue(), tail.get(0), tail.get(1));
            return HeadSplicing.assemble(hewHead, tail.subList(2, tail.size()));
        }
        return _n_.flatMap(n -> {
            BigInteger r = n.pow(m.intValue());
            return HeadSplicing.assemble(Symbol.of(r), tail.subList(1, tail.size()));
        });
    }

    private static Exp nestedInvocations(int n, Exp f, Exp x) {
        Exp result = x;
        for (int i = 0; i < n; i++) {
            result = Sexp.create(f, result);
        }
        return result;
    }
}
