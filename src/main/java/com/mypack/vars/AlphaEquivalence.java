package com.mypack.vars;

import com.mypack.eval.LambdaExpression;
import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.parser.LispParser;
import com.mypack.util.AsSexp;
import com.mypack.util.IsLambdaExpression;
import com.mypack.util.IsSexp;

import java.util.List;
import java.util.Optional;

public class AlphaEquivalence implements ExpVisitor<Boolean> {

    public static boolean eq(Exp exp1, Exp exp2) {
        return exp1.accept(new AlphaEquivalence(exp2));
    }

    public static boolean eq(String exp1, Exp exp2) {
        return LispParser.parse(exp1).accept(new AlphaEquivalence(exp2));
    }

    private final Exp target;

    public AlphaEquivalence(Exp target) {
        this.target = target;
    }

    @Override
    public Boolean visitSexp(Sexp sexp) {
        Optional<LambdaExpression> thisLambda = IsLambdaExpression.test(sexp);
        if (thisLambda.isPresent()) {
            Optional<LambdaExpression> targetLambda = IsLambdaExpression.test(target);
            if (targetLambda.isEmpty()) {
                return false;
            }
            Optional<LambdaExpression> newTarget = targetLambda.get().alpha(thisLambda.get().symbols());
            if (newTarget.isEmpty()) {
                return false;
            }
            return thisLambda.get().body().accept(new AlphaEquivalence(newTarget.get().body()));
        }
        if (!IsSexp.test(target)) {
            return false;
        }
        List<? extends Exp> targetSexp = AsSexp.get(target).asList();
        List<? extends Exp> asList = sexp.asList();
        for (int i = 0; i < asList.size(); i++) {
            Exp exp = asList.get(i);
            if (!exp.accept(new AlphaEquivalence(targetSexp.get(i)))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean visitSymbol(Symbol symbol) {
        return symbol.equals(target);
    }
}
