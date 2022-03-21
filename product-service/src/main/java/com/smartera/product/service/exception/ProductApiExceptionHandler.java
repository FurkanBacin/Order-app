package com.smartera.product.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ProductApiExceptionHandler {

    @ExceptionHandler(value = {ProductApiRequestException.class})
    public ResponseEntity<Object> handleCartApiException (ProductApiRequestException e){

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ProductApiException productApiException =new ProductApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(productApiException, badRequest);
    }
}
