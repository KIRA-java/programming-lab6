package ru.example.lab6.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse<Void>> handleStatus(
            ResponseStatusException exception) {

        return ResponseEntity
                .status(exception.getStatusCode())
                .body(new ApiResponse<>(
                        false,
                        exception.getReason(),
                        null
                ));
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<ApiResponse<Void>> handleBadRequest(
            Exception exception) {

        return ResponseEntity.badRequest().body(
                new ApiResponse<>(
                        false,
                        "Неверный JSON или тип данных в запросе",
                        null
                )
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleDatabaseError(
            DataIntegrityViolationException exception) {

        return ResponseEntity.badRequest().body(
                new ApiResponse<>(
                        false,
                        "Данные не соответствуют структуре таблицы",
                        null
                )
        );
    }
}