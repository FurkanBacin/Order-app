package com.smartera.product.service.exception;

public class ProductApiRequestException extends RuntimeException{

    public ProductApiRequestException(String message) {
        super(message);
    }

    public ProductApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
