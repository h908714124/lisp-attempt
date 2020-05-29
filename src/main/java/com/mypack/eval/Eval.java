package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.IsLambdaExpression;

import java.util.ArrayList;
import java.util.List;

public class Eval implements ExpVisitor<Exp> {

    @Override
    public Exp visitSexp(Sexp sexp) {
        if (sexp.tail().isEmpty()) {
            return sexp.head();
        }
        if (IsLambdaExpression.test(sexp.head())) {
            LambdaExpression lambda = LambdaExpression.create(sexp.head());
            if (lambda.symbols().isEmpty()) {
                throw new AssertionError("No symbols: " + sexp.head());
            }
            List<? extends Exp> args = sexp.tail();
            for (int i = 0; i < args.size(); i++) {
                Exp arg = args.get(i);
                Exp newBody = lambda.apply(arg);
                List<Symbol> newSymbols = lambda.symbols().subList(1, lambda.symbols().size());
                if (newSymbols.isEmpty()) {
                    List<? extends Exp> newTail = args.subList(i + 1, args.size());
                    if (newTail.isEmpty()) {
                        return newBody;
                    } else {
                        return Sexp.create(newBody, newTail);
                    }
                }
                lambda = new LambdaExpression(newSymbols, newBody);
            }
            return lambda.toExp();
        }
        boolean isLambda = IsLambdaExpression.test(sexp);
        Exp newHead = sexp.head().accept(this);
        List<Exp> result = new ArrayList<>(sexp.tail().size());
        List<? extends Exp> tail = sexp.tail();
        for (int i = 0; i < tail.size(); i++) {
            Exp exp = tail.get(i);
            if (isLambda && i == 0) { // Is exp a variableList?
                result.add(exp);
            } else {
                result.add(exp.accept(this));
            }
        }
        return Sexp.create(newHead, result);
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
