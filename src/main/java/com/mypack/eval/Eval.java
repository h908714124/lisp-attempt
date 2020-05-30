package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.IsLambdaExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Eval implements ExpVisitor<Exp> {

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
            Exp newBody = lambda.apply(args.get(0));
            List<Symbol> newSymbols = lambda.symbols().subList(1, lambda.symbols().size());
            if (newSymbols.isEmpty()) {
                if (newArgs.isEmpty()) {
                    return newBody;
                } else {
                    return Sexp.create(newBody, newArgs);
                }
            }
            Exp newLambda = new LambdaExpression(newSymbols, newBody).toExp();
            if (newArgs.isEmpty()) {
                return newLambda;
            } else {
                return Sexp.create(newLambda, newArgs);
            }
        }
        Optional<LambdaExpression> isLambda = IsLambdaExpression.test(sexp);
        if (isLambda.isPresent()) {
            LambdaExpression lambda = isLambda.get();
            return Sexp.create(Symbol.lambda(), Arrays.asList(Sexp.create(lambda.symbols()), lambda.body().accept(this)));
        }
        List<? extends Exp> exps = sexp.asList();
        List<Exp> result = new ArrayList<>(exps.size());
        for (Exp exp : exps) {
            result.add(exp.accept(this));
        }
        return Sexp.create(result);
    }

    static List<Exp> iterEval(Exp exp) {
        return iterEval(exp, 100);
    }

    static List<Exp> iterEval(Exp exp, int max) {
        Eval eval = new Eval();
        List<Exp> result = new ArrayList<>(max);
        int n = 0;
        String s;
        do {
            s = exp.toString();
            Exp newExp = exp.accept(eval);
            result.add(exp);
            n += 1;
            exp = newExp;
        } while (!exp.toString().equals(s) && n <= max);
        return result;
    }

    @Override
    public Exp visitSymbol(Symbol symbol) {
        return symbol;
    }
}
