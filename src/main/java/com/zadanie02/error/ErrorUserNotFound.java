package com.zadanie02;

import org.springframework.http.HttpStatus;

public record ErrorUserNotFound(HttpStatus status, String message) {
}
