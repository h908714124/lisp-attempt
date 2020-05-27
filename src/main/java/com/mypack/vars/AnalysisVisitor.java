package com.mypack.vars;

import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.AsSexp;
import com.mypack.util.AsSymbol;
import com.mypack.util.IsLambdaExpression;

import java.util.HashSet;
import java.util.Set;

public class AnalysisVisitor implements ExpVisitor<Void> {

    private final Set<Symbol> bound = new HashSet<>();

    private final Set<Symbol> unbound = new HashSet<>();

    @Override
    public Void visitSexp(Sexp sexp) {
        if (IsLambdaExpression.test(sexp)) {
            Sexp variableList = AsSexp.get(sexp.tail().get(0));
            variableList.asList().stream()
                    .map(AsSymbol::get)
                    .forEach(bound::add);
            sexp.tail().get(1).accept(this);
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
