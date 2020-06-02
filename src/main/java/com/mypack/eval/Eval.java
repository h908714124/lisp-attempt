package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.AsSymbol;
import com.mypack.util.IsLambdaExpression;
import com.mypack.util.IsSymbol;
import com.mypack.vars.AnalysisVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

class Eval implements ExpVisitor<Exp> {

    private final Set<Symbol> reserved;

    private final Environment env;

    private Eval(Environment env, Set<Symbol> reserved) {
        this.reserved = LambdaExpression.union(reserved, env.keySet());
        this.env = env;
    }

    Eval(Environment env) {
        this(env, Collections.emptySet());
    }

    @Override
    public Exp visitSexp(Sexp sexp) {
        if (sexp.tail().isEmpty()) {
            return sexp.head();
        }
        Optional<LambdaExpression> isHeadLambda = IsLambdaExpression.test(sexp.head());
        if (isHeadLambda.isPresent() && !sexp.tail().isEmpty()) {
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
        Optional<LambdaExpression> isLambda = IsLambdaExpression.test(sexp);
        if (isLambda.isPresent()) {
            LambdaExpression lambda = isLambda.get();
            Eval newEval = new Eval(env, LambdaExpression.union(reserved, lambda.symbols().symbols()));
            return new LambdaExpression(lambda.symbols(), lambda.body().accept(newEval)).toExp();
        }
        if (IsSymbol.test(sexp.head())) {
            Symbol symbol = AsSymbol.get(sexp.head());
            Exp definition = env.lookup(symbol);
            if (definition != null) {
                return insertDefinition(sexp, symbol, definition);
            }
        }
        List<? extends Exp> exps = sexp.asList();
        for (int i = 0; i < exps.size(); i++) {
            Exp exp = exps.get(i);
            Exp newExp = exp.accept(this);
            if (!newExp.toString().equals(exp.toString())) {
                List<Exp> result = new ArrayList<>(exps);
                result.set(i, newExp);
                return Sexp.create(result);
            }
        }
        return sexp;
    }

    private Exp insertDefinition(Sexp sexp, Symbol symbol, Exp definition) {
        Set<Symbol> all = LambdaExpression.union(AnalysisVisitor.analyse(sexp).all(), reserved);
        Symbol newSymbol = symbol;
        while (all.contains(newSymbol)) {
            newSymbol = Symbol.of(newSymbol.value() + "_");
        }
        Sexp newSexp = Sexp.create(newSymbol, sexp.tail());
        return new LambdaExpression(ParamBlock.create(newSymbol), newSexp).apply(definition, reserved);
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
