package com.mypack.exp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParamBlock implements Exp {

    private final List<Symbol> symbols;
    private final Symbol head;
    private final List<Symbol> tail;

    private ParamBlock(List<Symbol> symbols) {
        this.symbols = symbols;
        this.head = symbols.get(0);
        this.tail = symbols.subList(1, symbols.size());
    }

    public static ParamBlock create(List<Symbol> symbols) {
        return new ParamBlock(symbols);
    }

    public static ParamBlock create(Symbol symbol) {
        return new ParamBlock(List.of(symbol));
    }

    public static ParamBlock create(Symbol symbol1, Symbol symbol2) {
        return new ParamBlock(List.of(symbol1, symbol2));
    }

    public Symbol head() {
        return head;
    }

    public List<Symbol> tail() {
        return tail;
    }

    public boolean isEmpty() {
        return symbols.isEmpty();
    }

    public int size() {
        return symbols.size();
    }

    public List<Symbol> symbols() {
        return symbols;
    }

    @Override
    public <R, P> R accept(ExpVisitor<R, P> v, P p) {
        return v.visitParamBlock(this);
    }

    @Override
    public String toString() {
        List<String> strings = new ArrayList<>(symbols.size());
        for (Symbol symbol : symbols) {
            strings.add(symbol.toString());
        }
        return "[" + String.join(" ", strings) + ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParamBlock that = (ParamBlock) o;
        return symbols.equals(that.symbols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbols);
    }
}
