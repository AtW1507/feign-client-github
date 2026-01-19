package com.zadanie02.error;

import org.springframework.http.HttpStatus;

public record ErrorUserNotFound(HttpStatus status, String message) {
}
