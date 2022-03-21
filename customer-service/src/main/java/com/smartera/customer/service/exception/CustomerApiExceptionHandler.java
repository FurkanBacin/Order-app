package com.smartera.customer.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CustomerApiExceptionHandler {

    @ExceptionHandler(value = {CustomerApiRequestException.class})
    public ResponseEntity<Object> handleCartApiException (CustomerApiRequestException e){

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        CustomerApiException customerApiException =new CustomerApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(customerApiException, badRequest);
    }
}
