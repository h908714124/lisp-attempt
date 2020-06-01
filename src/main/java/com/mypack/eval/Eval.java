package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.AsSymbol;
import com.mypack.util.IsLambdaExpression;
import com.mypack.util.IsSymbol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

class Eval implements ExpVisitor<Exp> {

    private final Set<Symbol> reserved;

    private final Environment env;

    private Eval(Environment env, Set<Symbol> reserved) {
        this.reserved = reserved;
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
                return new LambdaExpression(ParamBlock.create(symbol), sexp).apply(definition, reserved);
            }
        }
        List<? extends Exp> exps = sexp.asList();
        List<Exp> result = new ArrayList<>(exps.size());
        for (Exp exp : exps) {
            result.add(exp.accept(this));
        }
        return Sexp.create(result);
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
