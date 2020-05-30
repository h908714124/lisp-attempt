package com.mypack.util;

import com.mypack.eval.LambdaExpression;
import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

public class FindNumbers implements ExpVisitor<Void> {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("0|[-]?[1-9]\\d*");

    private final Set<Symbol> result = new LinkedHashSet<>();

    private FindNumbers() {
    }

    public static Set<Symbol> search(Exp exp) {
        FindNumbers visitor = new FindNumbers();
        exp.accept(visitor);
        return visitor.result;
    }

    @Override
    public Void visitSexp(Sexp sexp) {
        Optional<LambdaExpression> lambda = IsLambdaExpression.test(sexp);
        if (lambda.isPresent()) {
            List<Symbol> variableList = lambda.get().symbols();
            for (Symbol symbol : variableList) {
                if (NUMBER_PATTERN.matcher(symbol.value()).matches()) {
                    throw new IllegalArgumentException("Not a symbol: " + symbol);
                }
            }
            lambda.get().body().accept(this);
            return null;
        }
        for (Exp exp : sexp.asList()) {
            exp.accept(this);
        }
        return null;
    }

    @Override
    public Void visitSymbol(Symbol symbol) {
        if (NUMBER_PATTERN.matcher(symbol.value()).matches()) {
            result.add(symbol);
        }
        return null;
    }
}
