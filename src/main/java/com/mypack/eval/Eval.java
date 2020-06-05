package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.AsSexp;
import com.mypack.util.AsSymbol;
import com.mypack.util.IsLambdaExpression;
import com.mypack.util.IsSexp;
import com.mypack.util.IsSymbol;
import com.mypack.vars.AlphaEquivalence;
import com.mypack.vars.AnalysisVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.mypack.eval.Environment.NUMBER_PATTERN;
import static com.mypack.eval.LambdaExpression.union;

class Eval implements ExpVisitor<Exp> {

    private final Set<Symbol> reserved;

    private final Environment env;

    private Eval(Environment env, Set<Symbol> reserved) {
        this.reserved = union(reserved, env.keySet());
        this.env = env;
    }

    Eval(Environment env) {
        this(env, Collections.emptySet());
    }

    @Override
    public Exp visitSexp(Sexp sexp) {
        return checkShortcuts(sexp)
                .or(() -> checkHeadLambda(sexp))
                .or(() -> checkHeadSymbol(sexp))
                .or(() -> recurseLambda(sexp))
                .or(() -> recurseParts(sexp.asList()))
                .orElse(sexp);
    }

    private Optional<Exp> recurseParts(List<? extends Exp> exps) {
        for (int i = 0; i < exps.size(); i++) {
            Exp exp = exps.get(i);
            Exp newExp = exp.accept(this);
            if (newExp != exp) {
                List<Exp> result = new ArrayList<>(exps);
                result.set(i, newExp);
                return Optional.of(Sexp.create(result));
            }
        }
        return Optional.empty();
    }

    private Optional<Exp> checkShortcuts(Sexp sexp) {
        Exp head = sexp.head();
        if (IsSymbol.test(head, "I") && sexp.size() == 2) {
            return Optional.of(sexp.get(1));
        }
        if (IsSymbol.test(head, "pred")) {
            return predShortcut(sexp);
        }
        if (IsSymbol.test(head, "zero?")) {
            return zeroShortcut(sexp);
        }
        if (IsSymbol.test(head, "1") && sexp.size() == 2) {
            return Optional.of(sexp.get(1));
        }
        if (IsSymbol.test(head, "1") && sexp.size() == 3) {
            return Optional.of(Sexp.create(sexp.get(1), sexp.get(2)));
        }
        if (isFalseSymbol(head) && sexp.size() == 3) {
            return Optional.of(sexp.get(2));
        }
        if (isTrueSymbol(head) && sexp.size() == 3) {
            return Optional.of(sexp.get(1));
        }
        if (IsSexp.test(head)) {
            return headSexpShortcut(sexp);
        }
        return Optional.empty();
    }

    private Optional<Exp> headSexpShortcut(Sexp sexp) {
        Sexp headSexp = AsSexp.get(sexp.head());
        if (isFalseSymbol(headSexp.head()) && headSexp.size() == 2 && sexp.size() == 2) {
            return Optional.of(sexp.get(1));
        }
        if (isTrueSymbol(headSexp.head()) && headSexp.size() == 2 && sexp.size() == 2) {
            return Optional.of(headSexp.get(1));
        }
        return Optional.empty();
    }

    private Optional<Exp> zeroShortcut(Sexp sexp) {
        if (sexp.size() != 2) {
            return Optional.empty();
        }
        if (IsSymbol.test(sexp.get(1), NUMBER_PATTERN)) {
            int i = Integer.parseInt(AsSymbol.get(sexp.get(1)).value());
            return Optional.of(i == 0 ? Symbol.of("true") : Symbol.of("false"));
        } else if (IsSexp.test(sexp.get(1))) {
            Optional<Exp> arg = checkShortcuts(AsSexp.get(sexp.get(1)));
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
            Optional<Exp> arg = checkShortcuts(AsSexp.get(sexp.get(1)));
            if (arg.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(Sexp.create(Symbol.of("pred"), arg.get()));
        } else {
            return Optional.empty();
        }
    }

    private boolean isTrueSymbol(Exp exp) {
        if (!IsSymbol.test(exp)) {
            return false;
        }
        String symbol = AsSymbol.get(exp).value();
        return symbol.equals("K") || symbol.equals("true");
    }

    private boolean isFalseSymbol(Exp exp) {
        if (!IsSymbol.test(exp)) {
            return false;
        }
        String symbol = AsSymbol.get(exp).value();
        return symbol.equals("false") || symbol.equals("0");
    }

    private Optional<Exp> checkHeadLambda(Sexp sexp) {
        Optional<LambdaExpression> isHeadLambda = IsLambdaExpression.test(sexp.head());
        if (isHeadLambda.isEmpty() || sexp.tail().isEmpty()) {
            return Optional.empty();
        }
        LambdaExpression lambda = isHeadLambda.get();
        List<? extends Exp> args = sexp.tail();
        Optional<List<Exp>> subst = tailSubstitute(args);
        if (subst.isPresent()) {
            return Optional.of(Sexp.create(sexp.head(), subst.get()));
        }
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
        Eval newEval = new Eval(env, union(reserved, lambda.symbols().symbols()));
        Exp newBody = lambda.body().accept(newEval);
        if (newBody == lambda.body()) {
            return Optional.of(sexp);
        }
        return Optional.of(Sexp.create(Symbol.fn(), lambda.symbols(), newBody));
    }

    private Optional<Exp> checkHeadSymbol(Sexp sexp) {
        if (!IsSymbol.test(sexp.head())) {
            return Optional.empty();
        }
        Symbol symbol = AsSymbol.get(sexp.head());
        Exp definition = env.lookup(symbol);
        if (definition == null) {
            return Optional.empty();
        }
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

    private Optional<Exp> substitute(Exp exp) {
        for (Map.Entry<Exp, Exp> alphaEntry : env.substitutionEntries()) {
            if (AlphaEquivalence.eq(alphaEntry.getKey(), exp)) {
                return Optional.of(alphaEntry.getValue());
            }
        }
        return Optional.empty();
    }

    private Optional<List<Exp>> tailSubstitute(List<? extends Exp> args) {
        List<Exp> result = new ArrayList<>(args);
        for (int i = 0; i < args.size(); i++) {
            Exp arg = args.get(i);
            Optional<Exp> subst = substitute(arg);
            if (subst.isPresent()) {
                result.set(i, subst.get());
                return Optional.of(result);
            }
        }
        return Optional.empty();
    }


    @Override
    public Exp visitSymbol(Symbol symbol) {
        return symbol;
    }

    @Override
    public Exp visitParamBlock(ParamBlock paramBlock) {
        return paramBlock;
    }
}
