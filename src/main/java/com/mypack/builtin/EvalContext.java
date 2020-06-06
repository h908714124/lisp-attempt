package com.mypack.builtin;

import com.mypack.eval.Environment;
import com.mypack.eval.Eval;
import com.mypack.eval.LambdaExpression;
import com.mypack.exp.Exp;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.AsSexp;
import com.mypack.util.AsSymbol;
import com.mypack.util.IsLambdaExpression;
import com.mypack.util.IsSexp;
import com.mypack.util.IsSymbol;
import com.mypack.vars.AnalysisVisitor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.mypack.builtin.Constants.TRUE;
import static com.mypack.eval.Environment.NUMBER_PATTERN;
import static com.mypack.eval.Environment.nestedInvocations;
import static com.mypack.util.SetUtil.union;

public class EvalContext {

    private final Environment env;

    private final Set<Symbol> reserved;

    public EvalContext(Environment env, Set<Symbol> reserved) {
        this.reserved = reserved;
        this.env = env;
    }

    public Exp eval(Sexp sexp) {
        return checkBuiltIns(sexp)
                .or(() -> checkRegularApplication(sexp))
                .or(() -> checkEnvLookup(sexp))
                .or(() -> recurseLambda(sexp))
                .or(() -> recurseParts(sexp.asList()))
                .orElse(sexp);
    }

    private Optional<Exp> recurseParts(List<? extends Exp> exps) {
        for (int i = 0; i < exps.size(); i++) {
            Exp exp = exps.get(i);
            Exp newExp = exp.accept(Eval.get(), this);
            if (newExp != exp) {
                List<Exp> result = new ArrayList<>(exps);
                result.set(i, newExp);
                return Optional.of(Sexp.create(result));
            }
        }
        return Optional.empty();
    }

    private Optional<Exp> checkBuiltIns(Sexp sexp) {
        Exp head = sexp.head();
        int size = sexp.size();
        if (IsSymbol.test(head, "I") && size == 2) {
            return Optional.of(sexp.get(1));
        }
        if (IsSymbol.test(head, "pred")) {
            return predShortcut(sexp);
        }
        if (IsSymbol.test(head, "zero?")) {
            return isZeroShortcut(sexp);
        }
        if (IsSymbol.test(head, "0") && size == 2) {
            return Optional.of(TRUE);
        }
        if (IsSymbol.test(head, "1") && size == 2) {
            return Optional.of(sexp.get(1));
        }
        if (IsSymbol.test(head, "*")) {
            return multBuiltIn(sexp);
        }
        if (IsSymbol.test(head, "+")) {
            return plusBuiltIn(sexp);
        }
        if (IsSymbol.test(head, NUMBER_PATTERN) && size >= 3) {
            Exp invocations = nestedInvocations(Integer.parseInt(AsSymbol.get(head).value()),
                    sexp.get(1), sexp.get(2));
            if (size == 3) {
                return Optional.of(invocations);
            }
            return Optional.of(Sexp.create(invocations, sexp.subList(3)));
        }
        if (IsFalse.test(head) && size >= 3) {
            if (size == 3) {
                return Optional.of(sexp.get(2));
            }
            return Optional.of(Sexp.create(sexp.get(2), sexp.subList(3)));
        }
        if (IsTrue.test(head) && size >= 3) {
            if (size == 3) {
                return Optional.of(sexp.get(1));
            }
            return Optional.of(Sexp.create(sexp.get(1), sexp.subList(3)));
        }
        if (IsSexp.test(head)) {
            return headSexpShortcut(sexp);
        }
        if (IsSymbol.test(head, "Y") && sexp.size() >= 2) {
            if (sexp.size() == 2) {
                return Optional.of(Sexp.create(sexp.get(1), sexp));
            }
            return Optional.of(Sexp.create(sexp.get(1), Sexp.create(Symbol.of("Y"), sexp.get(1)), sexp.subList(2)));
        }
        return Optional.empty();
    }

    private Optional<Exp> multBuiltIn(Sexp sexp) {
        BigInteger current = BigInteger.ONE;
        for (Exp exp : sexp.tail()) {
            if (!IsSymbol.test(exp, NUMBER_PATTERN)) {
                return Optional.empty();
            }
            current = current.multiply(new BigInteger(AsSymbol.get(exp).value()));
        }
        return Optional.of(Symbol.of(current.toString()));
    }

    private Optional<Exp> plusBuiltIn(Sexp sexp) {
        BigInteger current = BigInteger.ZERO;
        for (Exp exp : sexp.tail()) {
            if (!IsSymbol.test(exp, NUMBER_PATTERN)) {
                return Optional.empty();
            }
            current = current.add(new BigInteger(AsSymbol.get(exp).value()));
        }
        return Optional.of(Symbol.of(current.toString()));
    }

    private Optional<Exp> headSexpShortcut(Sexp outer) {
        Sexp head = AsSexp.get(outer.head());
        if (IsFalse.test(head.head()) && head.size() == 2 && outer.size() >= 2) {
            if (outer.size() == 2) {
                return Optional.of(outer.get(1));
            }
            return Optional.of(Sexp.create(outer.get(1), outer.subList(2)));
        }
        if (IsTrue.test(head.head()) && head.size() == 2 && outer.size() >= 2) {
            if (outer.size() == 2) {
                return Optional.of(head.get(1));
            }
            return Optional.of(Sexp.create(head.get(1), outer.subList(2)));
        }
        if (IsSymbol.test(head.head(), NUMBER_PATTERN) && head.size() == 2 && outer.size() >= 2) {
            Exp invocations = nestedInvocations(Integer.parseInt(AsSymbol.get(head.head()).value()),
                    head.get(1), outer.get(1));
            if (outer.size() == 2) {
                return Optional.of(invocations);
            }
            return Optional.of(invocations.accept(new Splicing(2), outer));
        }
        if (IsSymbol.test(head.head(), "Y") && head.size() == 2 && outer.size() >= 1) {
            return Optional.of(Sexp.create(head.get(1), head, outer.subList(1)));
        }
        return Optional.empty();
    }

    private Optional<Exp> isZeroShortcut(Sexp sexp) {
        if (sexp.size() != 2) {
            return Optional.empty();
        }
        if (IsSymbol.test(sexp.get(1), NUMBER_PATTERN)) {
            int i = Integer.parseInt(AsSymbol.get(sexp.get(1)).value());
            return Optional.of(i == 0 ? Symbol.of("true") : Symbol.of("false"));
        } else if (IsSexp.test(sexp.get(1))) {
            Optional<Exp> arg = checkBuiltIns(AsSexp.get(sexp.get(1)));
            if (arg.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(Sexp.create(Symbol.of("zero?"), arg.get()));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Exp> predShortcut(Sexp sexp) {
        if (sexp.size() != 2) {
            return Optional.empty();
        }
        if (IsSymbol.test(sexp.get(1), NUMBER_PATTERN)) {
            int i = Integer.parseInt(AsSymbol.get(sexp.get(1)).value());
            return Optional.of(Symbol.of(i == 0 ? "0" : Integer.toString(i - 1)));
        } else if (IsSexp.test(sexp.get(1))) {
            Optional<Exp> arg = checkBuiltIns(AsSexp.get(sexp.get(1)));
            if (arg.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(Sexp.create(Symbol.of("pred"), arg.get()));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Exp> checkRegularApplication(Sexp sexp) {
        Optional<LambdaExpression> isHeadLambda = IsLambdaExpression.test(sexp.head());
        if (isHeadLambda.isEmpty() || sexp.tail().isEmpty()) {
            return Optional.empty();
        }
        LambdaExpression lambda = isHeadLambda.get();
        List<? extends Exp> args = sexp.tail();
        List<? extends Exp> newArgs = args.subList(1, args.size());
        Exp newBody = lambda.apply(args.get(0), reserved);
        List<Symbol> newSymbols = lambda.symbols().tail();
        if (newSymbols.isEmpty()) {
            if (newArgs.isEmpty()) {
                return Optional.of(newBody);
            } else {
                return Optional.of(Sexp.create(newBody, newArgs));
            }
        }
        Exp newLambda = new LambdaExpression(ParamBlock.create(newSymbols), newBody).toExp();
        if (newArgs.isEmpty()) {
            return Optional.of(newLambda);
        } else {
            return Optional.of(Sexp.create(newLambda, newArgs));
        }
    }

    private Optional<Exp> recurseLambda(Sexp sexp) {
        Optional<LambdaExpression> isLambda = IsLambdaExpression.test(sexp);
        if (isLambda.isEmpty()) {
            return Optional.empty();
        }
        LambdaExpression lambda = isLambda.get();
        EvalContext newContext = new EvalContext(env, union(reserved, lambda.symbols().symbols()));
        Exp newBody = lambda.body().accept(Eval.get(), newContext);
        if (newBody == lambda.body()) {
            return Optional.of(sexp); // stop signal
        }
        return Optional.of(Sexp.create(Symbol.fn(), lambda.symbols(), newBody));
    }

    private Optional<Exp> checkEnvLookup(Sexp sexp) {
        if (!IsSymbol.test(sexp.head())) {
            return Optional.empty();
        }
        Symbol symbol = AsSymbol.get(sexp.head());
        Exp definition = env.lookup(symbol);
        if (definition == null) {
            return Optional.empty();
        }
        // We found a user defined symbol. No eval on its args! -> normal order
        return Optional.of(insertDefinition(sexp, symbol, definition));
    }

    private Exp insertDefinition(Sexp sexp, Symbol symbol, Exp definition) {
        Set<Symbol> all = union(AnalysisVisitor.analyse(sexp).all(), reserved);
        Symbol newSymbol = symbol;
        while (all.contains(newSymbol)) {
            newSymbol = Symbol.of(newSymbol.value() + "_");
        }
        Sexp newSexp = Sexp.create(newSymbol, sexp.tail());
        return new LambdaExpression(ParamBlock.create(newSymbol), newSexp).apply(definition, reserved);
    }
}
