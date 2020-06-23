package com.mypack.repl;

import com.mypack.eval.Environment;
import com.mypack.eval.LispParser;
import com.mypack.eval.ParserException;
import com.mypack.exp.Exp;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.Objects;

public class Repl {

    private final Environment env;

    public Repl(Environment env) {
        this.env = env;
    }

    private void doMain() throws IOException {
        Terminal terminal = TerminalBuilder.terminal();
        LineReader lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
        String line;
        while (!Objects.toString(line = lineReader.readLine("$ "), "").trim().equals("q")) {
            try {
                Exp exp = LispParser.parse(line);
                Exp result = env.eval(exp);
                terminal.writer().println(result.toString());
            } catch (ParserException e) {
                terminal.writer().println("Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try {
            new Repl(new Environment()).doMain();
        } catch (EndOfFileException e) {
            // ignore
        }
    }
}
