package com.mypack.repl;

import com.mypack.eval.Environment;
import com.mypack.exp.Exp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Repl {

    public static void main(String[] args) throws IOException {
        Environment env = new Environment();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            System.out.print("$ ");
            System.out.flush();
            while (!Objects.toString(line = reader.readLine(), "").trim().isEmpty()) {
                Exp exp = env.eval(line);
                System.out.println(exp.toString());
                System.out.print("$ ");
                System.out.flush();
            }
        }
    }
}
