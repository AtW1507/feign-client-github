package com.zadanie02.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zadanie02.model.Commit;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubResponseBranchesDto(String name,
                                        @JsonAlias("commit") Commit lastcommit) {
}
