package com.zadanie02.infrastructure.controller.error;

import org.springframework.http.HttpStatus;

public record ErrorUserNotFound(HttpStatus status, String message) {
}
