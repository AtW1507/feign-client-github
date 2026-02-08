package com.zadanie02.infrastructure.controller.dto.requestDatabase;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateRepoRequestDto(
        @NotNull(message = "Name cannot be null")
        @NotEmpty(message = "Name cannot be empty")
        String name,
        @NotNull(message = "Owner cannot be null")
        @NotEmpty(message = "Owner cannot be empty")
        String owner) {
}
