package fi.tuni.compse140.exercise1.exception;

public class ExternalAPIException extends RuntimeException {

    public ExternalAPIException(String message) {
        super(message);
    }

    public ExternalAPIException(String api, Throwable exc) {
        super("Error during communication with " + api, exc);
    }

}
