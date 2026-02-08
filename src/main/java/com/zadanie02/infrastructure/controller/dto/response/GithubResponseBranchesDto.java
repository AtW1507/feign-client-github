package com.zadanie02.infrastructure.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zadanie02.domain.model.Commit;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubResponseBranchesDto(String name,
                                        @JsonAlias("commit") Commit lastcommit) {
}
