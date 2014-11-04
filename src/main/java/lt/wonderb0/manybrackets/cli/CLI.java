package lt.wonderb0.manybrackets.cli;

public class CLI {
    public static void main(String[] args) {
        try {
            switch (args[0]) {
                case "single":
                    new SingleThreadedReader().read();
                    break;
                case "multi":
                    new MultiThreadedReader().read();
                    break;
                default:
                    System.err.println("Usage: java -jar manybrackets [single | multi]");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Usage: java -jar manybrackets [single | multi]");
        }
    }
}
