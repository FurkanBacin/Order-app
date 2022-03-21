package com.smartera.customer.service.exception;

public class CustomerApiRequestException extends RuntimeException{

    public CustomerApiRequestException(String message) {
        super(message);
    }

    public CustomerApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
