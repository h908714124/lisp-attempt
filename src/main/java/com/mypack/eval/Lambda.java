package com.mypack.eval;

import com.mypack.exp.Exp;
import com.mypack.exp.Sexp;
import com.mypack.vars.Mapping;
import com.mypack.vars.Vars;

import java.util.List;

public class Lambda {

    public static Exp createLambda(Sexp variableList, Exp body, List<Exp> args) {
        Vars vars = new Vars(variableList);
        Mapping mapping = vars.createMapping(args);
        return body.accept(mapping);
    }
}
