package com.smartera.cart.service.exception;

public class CartApiRequestException extends RuntimeException{
    public CartApiRequestException(String message) {
        super(message);
    }

    public CartApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
