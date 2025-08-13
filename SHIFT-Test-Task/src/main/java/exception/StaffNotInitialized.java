package exception;

public class StaffNotInitialized extends RuntimeException {
    public StaffNotInitialized(String message) {
        super(message);
    }
}
