package com.zadanie02.infrastructure.controller.error;

import org.springframework.http.HttpStatus;

public record ErrorHeader(HttpStatus status, String message) {
}
