package com.zadanie02.domain.service;

import com.zadanie02.domain.model.GithubRepo;
import com.zadanie02.domain.repository.GithubRepository;

import com.zadanie02.infrastructure.controller.error.UserNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class RepoRetriever {

    private final GithubRepository githubRepository;

    public RepoRetriever(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    public List<GithubRepo> findAll(Pageable pageable) {
        log.info("Retrieving all repos");
        return githubRepository.findAll(pageable);
    }

    public GithubRepo findById(Long id) {
        return githubRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public GithubRepo findByOwnerAndName(String owner, String name) {
        return githubRepository.findByOwnerAndName(owner, name).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public void existsById(Long id){
        if(!githubRepository.existsById(id)){
            throw new UserNotFoundException("User with id: " + id +  "not found");
        }
    }
}
