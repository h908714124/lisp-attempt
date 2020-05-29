package com.mypack.exp;

import com.mypack.vars.Freshness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Sexp implements Exp {

    private final Exp head;
    private final List<? extends Exp> tail;

    private Sexp(Exp head, List<? extends Exp> tail) {
        this.head = head;
        this.tail = tail;
    }

    public static Sexp create(Exp head, List<? extends Exp> tail) {
        Sexp result = new Sexp(head, tail);
        Optional<Symbol> symbol = Freshness.test(result);
        if (symbol.isPresent()) {
            throw new AssertionError("Non-fresh in " + result + ": " + symbol.get());
        }
        return result;
    }

    public static Sexp create(List<? extends Exp> list) {
        return new Sexp(list.get(0), list.subList(1, list.size()));
    }

    public Exp head() {
        return head;
    }

    public List<? extends Exp> tail() {
        return tail;
    }

    public List<? extends Exp> asList() {
        List<Exp> result = new ArrayList<>(tail.size() + 1);
        result.add(head);
        result.addAll(tail);
        return result;
    }

    @Override
    public <R> R accept(ExpVisitor<R> v) {
        return v.visitSexp(this);
    }

    @Override
    public String toString() {
        List<String> strings = new ArrayList<>();
        strings.add(head.toString());
        for (Exp exp : tail) {
            strings.add(exp.toString());
        }
        return "(" + String.join(" ", strings) + ')';
    }
}
