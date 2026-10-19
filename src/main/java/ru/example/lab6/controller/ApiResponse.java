package ru.example.lab6.controller;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data
) {
}