package com.zadanie02.domain.service;

import com.zadanie02.domain.repository.GithubRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Log4j2
public class RepoDeleter {

    private final GithubRepository githubRepository;
    private final RepoRetriever repoRetriever;

    public RepoDeleter(GithubRepository githubRepository, RepoRetriever repoRetriever) {
        this.githubRepository = githubRepository;
        this.repoRetriever = repoRetriever;
    }

    public void deleteById(Long id){
        repoRetriever.existsById(id);
        log.info("Repo with id: " + id + " deleted");
        githubRepository.deleteById(id);
    }
}
