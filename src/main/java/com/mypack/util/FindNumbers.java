package com.mypack.util;

import com.mypack.eval.LambdaExpression;
import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

public class FindNumbers implements ExpVisitor<Void, Void> {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("0|[-]?[1-9]\\d*");

    private final Set<Symbol> result = new LinkedHashSet<>();

    private FindNumbers() {
    }

    public static Set<Symbol> search(Exp exp) {
        FindNumbers visitor = new FindNumbers();
        exp.accept(visitor, null);
        return visitor.result;
    }

    @Override
    public Void visitSexp(Sexp sexp, Void _null) {
        Optional<LambdaExpression> lambda = IsLambdaExpression.test(sexp);
        if (lambda.isPresent()) {
            List<Symbol> variableList = lambda.get().symbols().symbols();
            for (Symbol symbol : variableList) {
                if (NUMBER_PATTERN.matcher(symbol.value()).matches()) {
                    throw new IllegalArgumentException("Not a symbol: " + symbol);
                }
            }
            lambda.get().body().accept(this, _null);
            return null;
        }
        for (Exp exp : sexp.asList()) {
            exp.accept(this, _null);
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

    @Override
    public Void visitParamBlock(ParamBlock paramBlock) {
        return null;
    }
}
