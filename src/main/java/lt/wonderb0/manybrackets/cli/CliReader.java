package lt.wonderb0.manybrackets.cli;

import lt.wonderb0.manybrackets.parsing.BracketParser;
import lt.wonderb0.manybrackets.validation.Validator;

import java.io.Console;

public abstract class CliReader {
    private BracketParser parser = new BracketParser();

    public void read() {
        Console console = System.console();
        String line;

        while ((line = console.readLine()) != null) {
            // Catch empty lines as well.
            if (line.isEmpty()) {
                return;
            }

            handleLine(line);
        }
    }

    boolean checkString(String tokens) {
        try {
            Validator validator = new Validator();
            return validator.validate(parser.parse(tokens));
        } catch (IllegalArgumentException e) {
            // Return false on illegal input, like "{a}
            return false;
        }
    }

    protected abstract void handleLine(String line);
}


