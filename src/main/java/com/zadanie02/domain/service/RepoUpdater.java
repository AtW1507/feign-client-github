package com.zadanie02.domain.service;

import com.zadanie02.domain.model.GithubRepo;
import com.zadanie02.domain.repository.GithubRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RepoUpdater {

    private final GithubRepository githubRepository;
    private final RepoRetriever repoRetriever;

    public RepoUpdater(GithubRepository githubRepository, RepoRetriever repoRetriever) {
        this.githubRepository = githubRepository;
        this.repoRetriever = repoRetriever;
    }

    public void updateById(Long id, GithubRepo newRecord) {
        repoRetriever.existsById(id);
        githubRepository.updateById(id, newRecord);
    }

    public GithubRepo updatePartiallyById(Long id, GithubRepo repoFromRequest) {
        GithubRepo repoFromDataBase = repoRetriever.findById(id);
        GithubRepo.GithubRepoBuilder builder = GithubRepo.builder();
        if(repoFromRequest.getName() != null) {
            builder.name(repoFromRequest.getName());
        }else{
            builder.name(repoFromDataBase.getName());
        }
        if(repoFromRequest.getOwner() != null){
            builder.owner(repoFromRequest.getOwner());
        }else{
            builder.owner(repoFromDataBase.getOwner());
        }
        GithubRepo toSave = builder.build();
        updateById(id, toSave);
        return toSave;
    }

}
