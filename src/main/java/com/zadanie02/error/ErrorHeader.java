package com.zadanie02;

import org.springframework.http.HttpStatus;

public record ErrorHeader(HttpStatus status, String message) {
}
