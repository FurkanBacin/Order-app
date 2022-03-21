package com.smartera.product.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ProductApiException {

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
}
