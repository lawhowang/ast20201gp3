package ast20201.project.exception;

public class OrderCancelledException extends Exception {

    OrderCancelledException() {

    }

    public OrderCancelledException(String message) {
        super(message);
    }
}