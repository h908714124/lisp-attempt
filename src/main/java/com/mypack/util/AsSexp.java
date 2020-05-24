package com.mypack.util;

import com.mypack.exp.Exp;
import com.mypack.exp.Sexp;

public class AsSexp {

    public static Sexp get(Exp exp) {
        return exp.accept(new CastingVisitor<>("sexp expected") {
            @Override
            public Sexp visitSexp(Sexp sexp) {
                return sexp;
            }
        });
    }
}
