package com.zadanie02.infrastructure.controller.client;


import com.zadanie02.infrastructure.controller.dto.response.GithubResponseBranchesDto;
import com.zadanie02.infrastructure.controller.dto.response.GithubResponseRepositoriesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "github-client")

public interface GithubClient {

    @GetMapping("users/{username}/repos")
    List<GithubResponseRepositoriesDto> getRepositories(@PathVariable String username);

    @GetMapping("repos/{owner}/{repo}/branches")
    List<GithubResponseBranchesDto> getBranches(@PathVariable String owner, @PathVariable String repo);

}
