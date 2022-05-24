package com.javachallenge.fileapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ApiFileException {

    private final boolean success = false;
    private final String message;
    private final List<String> validExtensions;
    private final HttpStatus httpStatus;
    private final LocalDateTime timestamp;

}
