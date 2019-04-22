package ast20201.project.exception;

public class InsufficientCreditsException extends Exception {

    InsufficientCreditsException() {

    }

    public InsufficientCreditsException(String message) {
        super(message);
    }
}