package com.smartera.cart.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Setter
public class CartApiException {

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;


}
