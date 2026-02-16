package com.loiane.controller;

import com.loiane.exception.RecordNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundExceptions(final RecordNotFoundException recordNotFoundException) {
        return recordNotFoundException.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentNotValidException(final MethodArgumentNotValidException methodArgumentNotValidException) {
        return methodArgumentNotValidException.getFieldErrors()
                .stream()
                .map(error -> error.getField().concat(" ").concat(error.getDefaultMessage()))
                .reduce("", (acc, error) -> acc.concat(error).concat("\n"));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(final ConstraintViolationException constraintViolationException) {
        return constraintViolationException.getConstraintViolations()
                .stream()
                .map(error -> error.getPropertyPath().toString().concat(" ").concat(error.getMessage()))
                .reduce("", (acc, error) -> acc.concat(error).concat("\n"));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException exception) {
        if (Objects.nonNull(exception) && Objects.nonNull(exception.getRequiredType())) {
            final var type = exception.getRequiredType().getTypeName();
            final String[] typeParts = type.split("\\.");
            final var typeName = typeParts[typeParts.length - 1];
            return exception.getName().concat(" should be of type ").concat(typeName);
        }
        return "Argument type not valid";
    }
}