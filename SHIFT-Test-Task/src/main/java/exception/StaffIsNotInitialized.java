package exception;

public class StaffIsNotInitialized extends RuntimeException {
    public StaffIsNotInitialized(String message) {
        super(message);
    }
}
