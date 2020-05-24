package com.mypack.eval;

import com.mypack.exp.EmptySexp;
import com.mypack.exp.Exp;
import com.mypack.exp.ExpVisitor;
import com.mypack.exp.Sexp;
import com.mypack.exp.Symbol;
import com.mypack.exp.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Eval implements ExpVisitor<Exp> {

    @Override
    public Exp visitEmptySexp(EmptySexp emptySexp) {
        return emptySexp;
    }

    @Override
    public Exp visitSexp(Sexp sexp) {
        if (sexp.head() instanceof Sexp && ((Sexp) sexp.head()).head() instanceof Symbol && ((Symbol) ((Sexp) sexp.head()).head()).value().equals("lambda")) {
            Function<List<Exp>, Exp> function = sexp.head().accept(new ExtractFunction());
            return function.apply(sexp.tail());
        }
        if (sexp.head() instanceof Symbol && ((Symbol) sexp.head()).value().equals("lambda")) {
            List<Exp> newTail = new ArrayList<>();
            List<Exp> tail = sexp.tail();
            newTail.add(tail.get(0));
            for (int i = 1; i < tail.size(); i++) {
                Exp exp = tail.get(i);
                newTail.add(exp.accept(this));
            }
            return new Sexp(Symbol.lambda(), newTail);
        }
        if (sexp.head() instanceof Symbol) {
            return sexp.head().accept(new ExtractFunction()).apply(sexp.tail());
        }
        Exp newHead = sexp.head().accept(this);
        ArrayList<Exp> result = new ArrayList<>();
        for (Exp exp : sexp.tail()) {
            result.add(exp.accept(this));
        }
        if (result.isEmpty()) {
            return newHead;
        } else {
            return new Sexp(newHead, result);
        }
    }

    static Exp iterEval(Exp exp) {
        String s;
        do {
            s = exp.toString();
            exp = exp.accept(new Eval());
        } while (!exp.toString().equals(s));
        return exp;
    }

    @Override
    public Exp visitValue(Value value) {
        return value;
    }

    @Override
    public Exp visitSymbol(Symbol symbol) {
        return symbol;
    }
}
