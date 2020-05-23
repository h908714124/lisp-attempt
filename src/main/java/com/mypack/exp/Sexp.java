package com.mypack.exp;

import java.util.ArrayList;
import java.util.List;

public class Sexp implements Exp {

    private final Exp head;
    private final List<Exp> tail;

    public Sexp(Exp head, List<Exp> tail) {
        this.head = head;
        this.tail = tail;
    }

    public Exp head() {
        return head;
    }

    public List<Exp> tail() {
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
