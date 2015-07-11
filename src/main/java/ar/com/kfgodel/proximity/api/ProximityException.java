package ar.com.kfgodel.proximity.api;

/**
 * This type represents an error in the proximity lib
 * Created by kfgodel on 11/07/15.
 */
public class ProximityException extends RuntimeException {

    public ProximityException(String message) {
        super(message);
    }

    public ProximityException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProximityException(Throwable cause) {
        super(cause);
    }
}
