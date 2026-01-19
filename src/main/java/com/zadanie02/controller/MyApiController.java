package com.zadanie02;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users/")
public class Controller {

    private final GithubService githubService;

    public Controller(final GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping(value = "{username}/repositories", produces = "application/json")
    public ResponseEntity<List<RepositoryInfo>> getRepositories(@PathVariable String username) {

        List<RepositoryInfo> repository = githubService.getRepositoriesWithBranches(username);
        return ResponseEntity.ok(repository);
    }
}
