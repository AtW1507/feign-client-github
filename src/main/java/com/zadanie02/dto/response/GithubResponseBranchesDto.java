package com.zadanie02;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubResponseBranchesDto(String name,
                                        @JsonAlias("commit") Commit lastcommit) {
}
