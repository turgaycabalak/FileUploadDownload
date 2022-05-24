package com.javachallenge.fileapi.exceptions;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Value(value = "${application.file.extensions}")
    private List<String> validExtensions;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        Map<String, Object> body = new HashMap<>();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("Errors: ",errors);
        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("Error", ex.getMessage());

        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintValidationException(ConstraintViolationException e){
        // 1. Create payload containing exception details
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiFileException apiFileException = new ApiFileException(
                e.getMessage(),
                validExtensions,
                httpStatus,
                LocalDateTime.now()
        );
        // 2. Return response entity
        return new ResponseEntity<>(apiFileException, httpStatus);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<Object> handleMultipartException(MultipartException e){
        // 1. Create payload containing exception details
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiFileException apiFileException = new ApiFileException(
                e.getMessage(),
                validExtensions,
                httpStatus,
                LocalDateTime.now()
        );
        // 2. Return response entity
        return new ResponseEntity<>(apiFileException, httpStatus);
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<Object> handleFileUploadException(FileUploadException e){
        // 1. Create payload containing exception details
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiFileException apiFileException = new ApiFileException(
                e.getMessage(),
                validExtensions,
                httpStatus,
                LocalDateTime.now()
        );
        // 2. Return response entity
        return new ResponseEntity<>(apiFileException, httpStatus);
    }

    @ExceptionHandler(FileProcessException.class)
    public ResponseEntity<Object> handleUploadFileException(FileProcessException e){
        // 1. Create payload containing exception details
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        ApiFileException apiFileException = new ApiFileException(
                e.getMessage(),
                validExtensions,
                httpStatus,
                LocalDateTime.now()
        );
        // 2. Return response entity
        return new ResponseEntity<>(apiFileException, httpStatus);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Object> handleFileNotFoundException(FileNotFoundException e){
        // 1. Create payload containing exception details
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        ApiFileException apiFileException = new ApiFileException(
                e.getMessage(),
                validExtensions,
                httpStatus,
                LocalDateTime.now()
        );
        // 2. Return response entity
        return new ResponseEntity<>(apiFileException, httpStatus);
    }

}
