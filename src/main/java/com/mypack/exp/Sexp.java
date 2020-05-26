package com.mypack.exp;

import java.util.ArrayList;
import java.util.List;

public class Sexp implements Exp {

    private final Exp head;
    private final List<? extends Exp> tail;

    public Sexp(Exp head, List<? extends Exp> tail) {
        this.head = head;
        this.tail = tail;
    }

    public static Sexp createArgumentList(List<? extends Exp> symbols) {
        return new Sexp(symbols.get(0), symbols.subList(1, symbols.size()));
    }

    public Exp head() {
        return head;
    }

    public List<? extends Exp> tail() {
        return tail;
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
