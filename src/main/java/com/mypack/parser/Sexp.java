package com.mypack.parser;

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
}
