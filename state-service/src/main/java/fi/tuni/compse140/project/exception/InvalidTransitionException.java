package fi.tuni.compse140.project.exception;

import fi.tuni.compse140.project.model.State;

public class InvalidTransitionException extends RuntimeException {

    public InvalidTransitionException(State from, State to) {
        super("The system cannot transition directly from " + State.INIT + " to " + State.RUNNING);
    }
}
