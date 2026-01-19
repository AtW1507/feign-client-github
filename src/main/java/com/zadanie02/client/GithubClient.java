package com.zadanie02;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "github-client", url = "https://api.github.com/")

public interface GithubClient {

    @GetMapping("users/{username}/repos")
    List<GithubResponseRepositoriesDto> getRepositories(@PathVariable String username);

    @GetMapping("repos/{owner}/{repo}/branches")
    List<GithubResponseBranchesDto> getBranches(@PathVariable String owner, @PathVariable String repo);

}
