package ast20201.project.exception;

import ast20201.project.model.Product;

public class InsufficientStockException extends Exception {

    private Product product;

    InsufficientStockException() {

    }

    public InsufficientStockException(String message, Product product) {
        super(message);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}