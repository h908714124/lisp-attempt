package com.mypack.parser;

public class EmptySexp implements Exp {

    private static final EmptySexp INSTANCE = new EmptySexp();

    public static EmptySexp instance() {
        return INSTANCE;
    }

    private EmptySexp() {
    }
}
