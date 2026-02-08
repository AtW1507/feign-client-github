package com.zadanie02.infrastructure.controller.dto.response.responseDatabase;

import org.springframework.http.HttpStatus;

public record DeleteRepoResponseDto(String message, HttpStatus status) {
}
