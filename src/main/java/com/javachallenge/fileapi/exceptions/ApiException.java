package com.javachallenge.fileapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiException {

    private final boolean success = false;
    private final String message;
    private final HttpStatus httpStatus;
    private final LocalDateTime timestamp;

}
