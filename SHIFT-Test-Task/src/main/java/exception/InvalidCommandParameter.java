package exception;

public class InvalidCommandParameter extends RuntimeException {
    public InvalidCommandParameter(String message) {
        super(message);
    }
}
