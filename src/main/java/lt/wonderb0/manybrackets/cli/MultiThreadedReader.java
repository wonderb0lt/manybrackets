package lt.wonderb0.manybrackets.cli;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedReader extends CliReader {
    private ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    private int threadCounter = 1;

    @Override
    protected void handleLine(String line) {
        executorService.submit(() -> System.out.println(String.valueOf(threadCounter++)
                        + ":"
                        + (checkString(line) ? "True" : "False")
        ));
    }
}
