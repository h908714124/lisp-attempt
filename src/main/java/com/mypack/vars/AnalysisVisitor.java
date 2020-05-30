package com.mypack.vars;

import com.mypack.eval.LambdaExpression;
import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.AsSymbol;
import com.mypack.util.IsLambdaExpression;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AnalysisVisitor implements ExpVisitor<Void> {

    private final Set<Symbol> bound = new HashSet<>();

    private final Set<Symbol> unbound = new HashSet<>();

    private AnalysisVisitor() {
    }

    @Override
    public Void visitSexp(Sexp sexp) {
        Optional<LambdaExpression> lambda = IsLambdaExpression.test(sexp);
        if (lambda.isPresent()) {
            List<Symbol> variableList = lambda.get().symbols();
            variableList.stream()
                    .map(AsSymbol::get)
                    .forEach(bound::add);
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
        if (!bound.contains(symbol)) {
            unbound.add(symbol);
        }
        return null;
    }

    public static AnalysisResult analyse(Exp exp) {
        AnalysisVisitor visitor = new AnalysisVisitor();
        exp.accept(visitor);
        return new AnalysisResult(visitor.bound, visitor.unbound);
    }
}
