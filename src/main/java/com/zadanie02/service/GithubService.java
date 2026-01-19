package com.zadanie02;

import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GithubService {

    private final GithubClient githubClient;

    public GithubService(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    public List<RepositoryInfo> getRepositoriesWithBranches(String username) {
        try {
            List<GithubResponseRepositoriesDto> repositories = githubClient.getRepositories(username);

            return repositories.stream()
                    .filter(repo -> !repo.fork())
                    .map(repo -> {
                        List<GithubResponseBranchesDto> branches = githubClient.getBranches(repo.owner().ownerLogin(),
                                repo.name()
                        );

                        List<BranchInfo> branchInfos = branches.stream().map(branch ->
                                        new BranchInfo(branch.name(), branch.lastcommit().sha()))
                                .toList();
                        return new RepositoryInfo(repo.name(), repo.owner().ownerLogin(), branchInfos);
                    }).toList();
        }catch (FeignException.NotFound exception){
            throw new UserNotFoundException("User not found: "  + username);
        }



    }
}

