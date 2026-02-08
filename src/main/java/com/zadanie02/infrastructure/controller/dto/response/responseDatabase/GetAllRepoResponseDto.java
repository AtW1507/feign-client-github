package com.zadanie02.infrastructure.controller.dto.response.responseDatabase;

import java.util.List;

public record GetAllRepoResponseDto(List<RepoDto> repos) {
}
