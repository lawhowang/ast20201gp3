package ast20201.project.exception;

public class OrderPaidException extends Exception {

    OrderPaidException() {

    }

    public OrderPaidException(String message) {
        super(message);
    }
}