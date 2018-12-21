package cars.exceptions;

public class DeserializeException extends RuntimeException {

    public DeserializeException(String message) {
        super(message);
    }

    public DeserializeException(String message, Throwable cause) {
        super(message, cause);
    }
}
