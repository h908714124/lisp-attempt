package com.mypack.builtin;

import com.mypack.exp.Exp;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.AsSexp;
import com.mypack.util.AsSymbol;
import com.mypack.util.IsChurchNumeral;
import com.mypack.util.IsSexp;
import com.mypack.util.IsSymbol;
import com.mypack.vars.AlphaEquivalence;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;

import static com.mypack.builtin.HeadSplicing.assemble;

public class Applicative {

    static final Pattern NUMBER_PATTERN = Pattern.compile("0|[1-9]\\d*");

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
        m.put(Symbol.of("pred"), tail -> {
            if (tail.isEmpty()) {
                return Optional.empty();
            }
            return evalNumber(tail.get(0)).flatMap(i -> {
                i = i.subtract(BigInteger.ONE);
                if (i.signum() == -1) {
                    return Optional.<Exp>empty(); // no negative numbers
                }
                return assemble(Symbol.of(i.toString()), tail, 1);
            });
        });
        Function<List<? extends Exp>, Optional<Exp>> inc = tail -> {
            if (tail.isEmpty()) {
                return Optional.empty();
            }
            return evalNumber(tail.get(0)).flatMap(i -> {
                i = i.add(BigInteger.ONE);
                return assemble(Symbol.of(i.toString()), tail, 1);
            });
        };
        m.put(Symbol.of("succ"), inc);
        m.put(Symbol.of("inc"), inc);
        m.put(Symbol.of("zero?"), tail -> {
            if (tail.size() < 3) {
                return Optional.empty();
            }
            return evalNumber(tail.get(0)).flatMap(i -> {
                Exp newHead = i.equals(BigInteger.ZERO) ? tail.get(1) : tail.get(2);
                return assemble(newHead, tail, 3);
            });
        });
        m.put(Symbol.of("*"), tail -> {
            BigInteger current = BigInteger.ONE;
            for (Exp exp : tail) {
                Optional<BigInteger> e = evalNumber(exp);
                if (e.isEmpty()) {
                    return Optional.empty();
                }
                current = current.multiply(e.get());
            }
            return Optional.of(Symbol.of(current.toString()));
        });
        m.put(Symbol.of("="), tail -> {
            if (tail.size() < 2) {
                return Optional.empty();
            }
            Optional<BigInteger> a = evalNumber(tail.get(0));
            Optional<BigInteger> b = evalNumber(tail.get(1));
            boolean eq = eq(tail.get(0), tail.get(1), a, b);
            Symbol r = Symbol.of(Boolean.toString(eq));
            return assemble(r, tail, 2);
        });
        m.put(Symbol.of("+"), tail -> {
            BigInteger current = BigInteger.ZERO;
            for (Exp exp : tail) {
                Optional<BigInteger> e = evalNumber(exp);
                if (e.isEmpty()) {
                    return Optional.empty();
                }
                current = current.add(e.get());
            }
            return Optional.of(Symbol.of(current.toString()));
        });
        m.put(Symbol.of("-"), tail -> {
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
                    return assemble(Symbol.of(r.toString()), tail, 2);
                });
            });
        });
        m.put(Symbol.of("false"), tail -> {
            if (tail.size() < 2) {
                return Optional.empty();
            }
            return assemble(tail.get(1), tail, 2);
        });
        m.put(Symbol.of("true"), tail -> {
            if (tail.size() < 2) {
                return Optional.empty();
            }
            return assemble(tail.get(0), tail, 2);
        });
        this.map = Map.copyOf(m);
    }

    private boolean eq(Exp tail0, Exp tail1, Optional<BigInteger> a, Optional<BigInteger> b) {
        if (a.isPresent() && b.isPresent()) {
            return a.get().equals(b.get());
        }
        if (a.isPresent()) {
            return eq(tail1, tail0, b, a);
        }
        if (b.isPresent()) {
            Optional<BigInteger> aa = IsChurchNumeral.test(tail0);
            if (aa.isEmpty()) {
                return false;
            }
            return aa.get().equals(b.get());
        }
        return AlphaEquivalence.eq(tail0, tail1);
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

    private Optional<Exp> applyNumberBuiltIn(BigInteger m, List<? extends Exp> t) {
        if (t.isEmpty()) {
            return Optional.empty();
        }
        return t.get(0).accept(new ApplyNumber(m), t);
    }
}
