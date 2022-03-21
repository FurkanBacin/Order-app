package com.smartera.cart.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CartApiExceptionHandler {

    @ExceptionHandler(value = {CartApiRequestException.class})
    public ResponseEntity<Object> handleCartApiException (CartApiRequestException e){

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        CartApiException cartApiException =new CartApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(cartApiException, badRequest);
    }
}
