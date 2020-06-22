package com.mypack.repl;

import com.mypack.eval.Environment;
import com.mypack.exp.Exp;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.Objects;

public class Repl {

    public static void main(String[] args) throws IOException {
        Environment env = new Environment();
        Terminal terminal = TerminalBuilder.terminal();
        LineReader lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
        String line;
        while (!Objects.toString(line = lineReader.readLine("$ "), "").trim().isEmpty()) {
            Exp exp = env.eval(line);
            terminal.writer().println(exp.toString());
        }
    }
}
