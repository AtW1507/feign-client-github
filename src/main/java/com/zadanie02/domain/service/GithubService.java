package com.zadanie02.domain.service;

import com.zadanie02.infrastructure.controller.client.GithubClient;

import com.zadanie02.infrastructure.controller.dto.response.BranchInfo;
import com.zadanie02.infrastructure.controller.dto.response.GithubResponseBranchesDto;
import com.zadanie02.infrastructure.controller.dto.response.GithubResponseRepositoriesDto;
import com.zadanie02.infrastructure.controller.dto.response.RepositoryInfo;

import com.zadanie02.infrastructure.controller.error.UserNotFoundException;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class GithubService {

    private final GithubClient githubClient;
    private final RepoAdder repoAdder;

    public GithubService(GithubClient githubClient, RepoAdder repoAdder) {
        this.githubClient = githubClient;
        this.repoAdder = repoAdder;
    }

    public List<RepositoryInfo> getRepositoriesWithBranches(String username) {
        try {
            List<GithubResponseRepositoriesDto> repositories = githubClient.getRepositories(username);

            for(GithubResponseRepositoriesDto repo : repositories) {
                if(!repo.fork()){
                    String owner = repo.owner().ownerLogin();
                    String name = repo.name();
                    repoAdder.saveIfNotExist(owner, name);
                }
            }


            List<CompletableFuture<RepositoryInfo>> repositoryInfos = repositories.stream()
                    .filter(repo -> !repo.fork())
                    .map(repo ->
                            CompletableFuture.supplyAsync(() -> {
                                List<GithubResponseBranchesDto> branches = githubClient.getBranches(repo.owner().ownerLogin(),
                                        repo.name()
                                );

                                List<BranchInfo> branchInfos = branches.stream().map(branch ->
                                                new BranchInfo(branch.name(), branch.lastcommit().sha()))
                                        .toList();
                                return new RepositoryInfo(repo.name(), repo.owner().ownerLogin(), branchInfos);
                            })).toList();
            return repositoryInfos.stream().map(CompletableFuture::join).toList();
        } catch (FeignException.NotFound exception) {
            throw new UserNotFoundException("User not found: " + username);
        }


    }
}

