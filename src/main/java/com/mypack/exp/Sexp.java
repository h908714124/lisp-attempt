package com.mypack.exp;

import java.util.ArrayList;
import java.util.List;

public class Sexp implements Exp {

    private final Exp head;
    private final List<? extends Exp> tail;
    private final List<? extends Exp> asList;

    private Sexp(Exp head, List<? extends Exp> tail) {
        this.head = head;
        this.tail = tail;
        List<Exp> asList = new ArrayList<>(tail.size() + 1);
        asList.add(head);
        asList.addAll(tail);
        this.asList = asList;
    }

    private Sexp(Exp head, List<? extends Exp> tail, List<? extends Exp> asList) {
        this.head = head;
        this.tail = tail;
        this.asList = asList;
    }

    public static Sexp create(Exp head, List<? extends Exp> tail) {
        return new Sexp(head, tail);
    }

    public static Sexp create(List<? extends Exp> list) {
        return new Sexp(list.get(0), list.subList(1, list.size()), list);
    }

    public static Sexp create(Exp head, Exp tail) {
        return new Sexp(head, List.of(tail));
    }

    public static Sexp create(Exp head, Exp tail1, Exp tail2) {
        return new Sexp(head, List.of(tail1, tail2));
    }

    public Exp head() {
        return head;
    }

    public List<? extends Exp> tail() {
        return tail;
    }

    public List<? extends Exp> asList() {
        return asList;
    }

    public int size() {
        return 1 + tail.size();
    }

    public Exp get(int i) {
        return asList.get(i);
    }

    @Override
    public <R> R accept(ExpVisitor<R> v) {
        return v.visitSexp(this);
    }

    @Override
    public String toString() {
        List<String> strings = new ArrayList<>(asList.size());
        for (Exp exp : asList) {
            strings.add(exp.toString());
        }
        return "(" + String.join(" ", strings) + ')';
    }
}
