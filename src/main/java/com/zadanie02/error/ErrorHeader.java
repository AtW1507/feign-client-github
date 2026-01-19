package com.zadanie02.error;

import org.springframework.http.HttpStatus;

public record ErrorHeader(HttpStatus status, String message) {
}
