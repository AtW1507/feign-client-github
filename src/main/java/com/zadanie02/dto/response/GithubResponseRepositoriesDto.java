package com.zadanie02;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubResponseRepositoriesDto(String name, Owner owner, boolean fork) {
}
