package br.com.inatel.testmarlon.core.errors;


public class NonexistentStockException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NonexistentStockException(String message) {
        super(message);
    }
}
