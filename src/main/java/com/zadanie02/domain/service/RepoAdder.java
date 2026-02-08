package com.zadanie02.domain.service;

import com.zadanie02.domain.model.GithubRepo;
import com.zadanie02.domain.repository.GithubRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@Transactional
public class RepoAdder {
    private final GithubRepository githubRepository;

    public RepoAdder(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    public GithubRepo addRepo(GithubRepo githubRepo) {
        return githubRepository.save(githubRepo);
    }

    public GithubRepo saveIfNotExist(String owner, String name) {
        Optional<GithubRepo> existingRepo = githubRepository.findByOwnerAndName(owner, name);
        if (existingRepo.isPresent()) {
            log.info("Repo with owner: " + owner + " and name: " + name + " already exists");
            return existingRepo.get();

        }

        GithubRepo newRepo = new GithubRepo(owner, name);
        GithubRepo saved = githubRepository.save(newRepo);
        log.info("Repo with owner: " + owner + " and name: " + name + " saved");
        return saved;
    }
}
