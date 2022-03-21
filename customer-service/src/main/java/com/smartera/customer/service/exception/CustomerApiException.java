package com.smartera.customer.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Setter
public class CustomerApiException {

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
}
