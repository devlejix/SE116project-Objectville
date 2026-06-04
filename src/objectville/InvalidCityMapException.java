package objectville;

// Arda

public class InvalidCityMapException extends RuntimeException {

    public InvalidCityMapException(String message) {
        super(message);
    }

    public InvalidCityMapException(String message, Throwable cause) {
        super(message, cause);
    }
}