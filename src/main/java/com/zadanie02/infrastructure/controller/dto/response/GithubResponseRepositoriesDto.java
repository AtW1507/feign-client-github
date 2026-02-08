package com.zadanie02.infrastructure.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zadanie02.domain.model.Owner;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubResponseRepositoriesDto(String name, Owner owner, boolean fork) {
}
