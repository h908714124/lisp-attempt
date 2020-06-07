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
import static com.mypack.builtin.HeadSplicing.trySplicing;
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
        return HeadSplicing.trySplicing(sexp)
                .or(() -> checkBuiltIns(sexp))
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

    private Optional<Exp> checkBuiltIns(Exp exp) {
        return IsSexp.test(exp) ? checkBuiltIns(AsSexp.get(exp)) : Optional.empty();
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
            return applyNumberBuiltIn(sexp);
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
        if (IsSymbol.test(head, "Y") && sexp.size() >= 2) {
            if (sexp.size() == 2) {
                return Optional.of(Sexp.create(sexp.get(1), sexp));
            }
            return Optional.of(Sexp.create(sexp.get(1), Sexp.create(Symbol.of("Y"), sexp.get(1)), sexp.subList(2)));
        }
        return Optional.empty();
    }

    private static Optional<Exp> applyNumberBuiltIn(Sexp sexp) {
        Exp invocations = nestedInvocations(Integer.parseInt(AsSymbol.get(sexp.head()).value()),
                sexp.get(1), sexp.get(2));
        return Optional.of(HeadSplicing.trySplicing(Sexp.create(invocations, sexp.subList(3))).orElse(invocations));
    }

    private Optional<Exp> multBuiltIn(Sexp sexp) {
        BigInteger current = BigInteger.ONE;
        for (Exp exp : sexp.tail()) {
            Exp e = checkBuiltIns(exp).orElse(exp);
            if (!IsSymbol.test(e, NUMBER_PATTERN)) {
                return Optional.empty();
            }
            current = current.multiply(new BigInteger(AsSymbol.get(e).value()));
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

    private Optional<Exp> isZeroShortcut(Sexp sexp) {
        if (sexp.size() < 4) {
            return Optional.empty();
        }
        Exp arg = checkBuiltIns(sexp.get(1)).orElse(sexp.get(1));
        if (IsSymbol.test(arg, NUMBER_PATTERN)) {
            int i = Integer.parseInt(AsSymbol.get(arg).value());
            Exp newHead = i == 0 ? sexp.get(2) : sexp.get(3);
            return Optional.of(HeadSplicing.trySplicing(Sexp.create(newHead, sexp.subList(4))).orElse(newHead));
        }
        return Optional.empty();
    }

    private Optional<Exp> predShortcut(Sexp sexp) {
        if (sexp.size() < 2) {
            return Optional.empty();
        }
        Exp arg = checkBuiltIns(sexp.get(1)).orElse(sexp.get(1));
        if (IsSymbol.test(arg, NUMBER_PATTERN)) {
            int i = Integer.parseInt(AsSymbol.get(arg).value());
            return Optional.of(Symbol.of(Integer.toString(i - 1)));
        }
        return Optional.empty();
    }

    private Optional<Exp> checkRegularApplication(Sexp sexp) {
        Exp result = sexp;
        Exp newSexp;
        while ((newSexp = apply(result)) != result) {
            result = newSexp;
        }
        if (newSexp == sexp) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    private Exp apply(Exp exp) {
        if (!IsSexp.test(exp)) {
            return exp;
        }
        Sexp sexp = AsSexp.get(exp);
        Optional<LambdaExpression> isHeadLambda = IsLambdaExpression.test(sexp.head());
        if (isHeadLambda.isEmpty() || sexp.tail().isEmpty()) {
            return sexp;
        }
        LambdaExpression lambda = isHeadLambda.get();
        List<? extends Exp> args = sexp.tail();
        List<? extends Exp> newArgs = args.subList(1, args.size());
        Exp newBody = lambda.apply(args.get(0), reserved);
        List<Symbol> newSymbols = lambda.symbols().tail();
        if (newSymbols.isEmpty()) {
            if (newArgs.isEmpty()) {
                return newBody;
            } else {
                return Sexp.create(newBody, newArgs);
            }
        }
        Exp newLambda = new LambdaExpression(ParamBlock.create(newSymbols), newBody).toExp();
        if (newArgs.isEmpty()) {
            return newLambda;
        } else {
            return Sexp.create(newLambda, newArgs);
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
        Exp result = new LambdaExpression(ParamBlock.create(newSymbol), newSexp).apply(definition, reserved);
        return trySplicing(AsSexp.get(result)).orElse(result);
    }
}
