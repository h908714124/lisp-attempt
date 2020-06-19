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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mypack.builtin.HeadSplicing.trySplicing;
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

    private Optional<Exp> checkBuiltIns(Sexp sexp) {
        Exp head = sexp.head();
        Applicative applicative = Applicative.get();
        Optional<Exp> result = applicative.eval(sexp);
        if (result.isPresent()) {
            return result;
        }
        if (IsSymbol.test(head, "Y") && sexp.size() >= 2) {
            if (sexp.size() == 2) {
                return Optional.of(Sexp.create(sexp.get(1), sexp));
            }
            return Optional.of(Sexp.create(sexp.get(1), Sexp.create(Symbol.of("Y"), sexp.get(1)), sexp.subList(2)
                    .stream()
                    .map(x -> applicative.tryEval(x)
                            .map(y -> Symbol.of(y.toString()))
                            .map(y -> (Exp) y)
                            .orElse(x))
                    .collect(Collectors.toList())));
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
