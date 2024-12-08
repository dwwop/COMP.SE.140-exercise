package fi.tuni.compse140.project.exception;

public class InvalidStateException extends RuntimeException {
    public InvalidStateException(String state) {
        super("State '" + state + "' is not a valid state value");
    }
}
