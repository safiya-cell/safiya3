
public class InvalidDriverException extends Exception {
    // Constructors
    public InvalidDriverException() {
        super("Invalid driver");
    }

    public InvalidDriverException(String message) {
        super(message);
    }
}