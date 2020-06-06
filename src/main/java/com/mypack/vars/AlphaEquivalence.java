package com.mypack.vars;

import com.mypack.eval.LambdaExpression;
import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.AsSexp;
import com.mypack.util.IsLambdaExpression;
import com.mypack.util.IsSexp;

import java.util.List;
import java.util.Optional;

public class AlphaEquivalence implements ExpVisitor<Boolean, Void> {

    private final Exp target;

    private AlphaEquivalence(Exp target) {
        this.target = target;
    }

    public static boolean eq(Exp exp1, Exp exp2) {
        return exp1.accept(new AlphaEquivalence(exp2), null);
    }

    @Override
    public Boolean visitSexp(Sexp sexp, Void _null) {
        Optional<LambdaExpression> thisLambda = IsLambdaExpression.test(sexp);
        if (thisLambda.isPresent()) {
            Optional<LambdaExpression> targetLambda = IsLambdaExpression.test(target);
            if (targetLambda.isEmpty()) {
                return false;
            }
            Optional<LambdaExpression> newTarget = alpha(targetLambda.get(), thisLambda.get().symbols().symbols());
            if (newTarget.isEmpty()) {
                return false;
            }
            return thisLambda.get().body().accept(new AlphaEquivalence(newTarget.get().body()), null);
        }
        if (!IsSexp.test(target)) {
            return false;
        }
        List<? extends Exp> targetSexp = AsSexp.get(target).asList();
        List<? extends Exp> asList = sexp.asList();
        for (int i = 0; i < asList.size(); i++) {
            Exp exp = asList.get(i);
            if (!exp.accept(new AlphaEquivalence(targetSexp.get(i)), null)) {
                return false;
            }
        }
        return true;
    }

    private static Optional<LambdaExpression> alpha(LambdaExpression lambda, List<Symbol> newSymbols) {
        if (newSymbols.size() != lambda.symbols().size()) {
            return Optional.empty();
        }
        Exp newBody = lambda.body();
        for (int i = 0; i < lambda.symbols().size(); i++) {
            newBody = BetaVisitor.replace(newBody, lambda.symbols().symbols().get(i), newSymbols.get(i));
        }
        return Optional.of(new LambdaExpression(ParamBlock.create(newSymbols), newBody));
    }

    @Override
    public Boolean visitSymbol(Symbol symbol, Void _null) {
        return symbol.equals(target);
    }

    @Override
    public Boolean visitParamBlock(ParamBlock paramBlock) {
        return paramBlock.equals(target);
    }
}
