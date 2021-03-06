package com.mypack.vars;

import com.mypack.eval.LambdaExpression;
import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.ParamBlock;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.util.IsLambdaExpression;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AnalysisVisitor implements ExpVisitor<Void, Void> {

    private final Set<Symbol> bound = new HashSet<>();

    private final Set<Symbol> unbound = new HashSet<>();

    private AnalysisVisitor() {
    }

    @Override
    public Void visitSexp(Sexp sexp, Void _null) {
        Optional<LambdaExpression> lambda = IsLambdaExpression.test(sexp);
        if (lambda.isPresent()) {
            bound.addAll(lambda.get().symbols().symbols());
            lambda.get().body().accept(this, null);
            return null;
        }
        for (Exp exp : sexp.asList()) {
            exp.accept(this, null);
        }
        return null;
    }

    @Override
    public Void visitSymbol(Symbol symbol, Void _null) {
        if (!bound.contains(symbol)) {
            unbound.add(symbol);
        }
        return null;
    }

    @Override
    public Void visitParamBlock(ParamBlock paramBlock) {
        for (Symbol symbol : paramBlock.symbols()) {
            symbol.accept(this, null);
        }
        return null;
    }

    public static AnalysisResult analyse(Exp exp) {
        AnalysisVisitor visitor = new AnalysisVisitor();
        exp.accept(visitor, null);
        return new AnalysisResult(visitor.bound, visitor.unbound);
    }
}
