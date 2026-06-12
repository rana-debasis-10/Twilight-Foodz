package com.twilight.eHandlers;
import com.twilight.dataTransferObjects.Error;
import com.twilight.exceptions.GeocodingError;
import com.twilight.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(SqlException.class)
    public ResponseEntity<Error> handleSqlExceptions(SqlException ex){
        Error error = new Error("SQL Error",ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Error> handleBadRequestExceptions(BadRequestException ex){
        Error error = new Error("Bad Request", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Error> handleNotFoundExceptions(NotFoundException ex){
        Error error = new Error("Not Found", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<Error> handleUnauthorizedExceptions(UnAuthorizedException ex){
        Error error = new Error("Unauthorized", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(GeocodingError.class)
    public ResponseEntity<Error> handleGeocodingExceptions(GeocodingError ex){
        Error error = new Error("Geocoding Error", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
    @ExceptionHandler(SomethingWentWrongException.class)
    public ResponseEntity<Error> handleServerExceptions(SomethingWentWrongException ex){
        Error error = new Error("Something went wrong", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<Error> handleFileTypeExceptions(GeocodingError ex){
        Error error = new Error("Something went wrong", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}
