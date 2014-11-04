package lt.wonderb0.manybrackets.cli;

public class SingleThreadedReader extends CliReader {
    protected void handleLine(String line) {
        // Why the capitalisation?
        System.out.println(checkString(line) ? "True" : "False");
    }
}
