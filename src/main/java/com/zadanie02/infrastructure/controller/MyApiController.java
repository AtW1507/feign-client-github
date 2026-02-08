package com.zadanie02.infrastructure.controller;

import com.zadanie02.domain.model.GithubRepo;
import com.zadanie02.domain.service.*;

import com.zadanie02.infrastructure.controller.dto.requestDatabase.CreateRepoRequestDto;
import com.zadanie02.infrastructure.controller.dto.requestDatabase.PartiallyUpdateRepoRequestDto;
import com.zadanie02.infrastructure.controller.dto.requestDatabase.UpdateRepoRequestDto;
import com.zadanie02.infrastructure.controller.dto.response.RepositoryInfo;
import com.zadanie02.infrastructure.controller.dto.response.responseDatabase.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import static com.zadanie02.infrastructure.controller.RepoMapper.*;
@Log4j2
@RestController
@RequestMapping("users/")
@AllArgsConstructor
public class MyApiController {

    private final GithubService githubService;
    private final RepoRetriever repoRetriever;
    private final RepoAdder repoAdder;
    private final RepoUpdater repoUpdater;
    private final RepoDeleter repoDeleter;


    @GetMapping(value = "{username}/repositories", produces = "application/json")
    public ResponseEntity<List<RepositoryInfo>> getRepositories(@PathVariable String username) {

        List<RepositoryInfo> repository = githubService.getRepositoriesWithBranches(username);
        return ResponseEntity.ok(repository);
    }

    @GetMapping("repos")
    public ResponseEntity<GetAllRepoResponseDto> getAllFromDataBase(@PageableDefault(page = 0, size = 10) Pageable pageable){
        List<GithubRepo> allRepo = repoRetriever.findAll(pageable);
        GetAllRepoResponseDto response = mapFromRepoToGetAllReposResponseDto(allRepo);
        return ResponseEntity.ok(response);
    }
    @GetMapping("repos/{id}")
    public ResponseEntity<GetRepoResponseDto> getRepoById(@PathVariable Long id, @RequestHeader(required = false)String requestId){
        log.info(requestId);
        GithubRepo repo = repoRetriever.findById(id);
        GetRepoResponseDto response = mapFromRepoTOGetRepoResponseDto(repo);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<CreateRepoResponseDto> postRepo(@RequestBody CreateRepoRequestDto request){
        GithubRepo repo = mapFromCreateRepoRequestDtoToRepo(request);
        GithubRepo savedRepo = repoAdder.addRepo(repo);
        CreateRepoResponseDto response = mapFromRepoToCreateRepoRequestDto(savedRepo);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteRepoResponseDto> deleteRepoIdUsingPathVariable(@PathVariable Long id){
        repoDeleter.deleteById(id);
        DeleteRepoResponseDto body = mapFromRepoToDeleteRepoResponseDto(id);
        return ResponseEntity.ok(body);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UpdateRepoResponseDto> update(@PathVariable Long id, @RequestBody @Valid UpdateRepoRequestDto request){
        GithubRepo newRepo = mapFromUpdateRepoRequestDtoToRepo(request);
        repoUpdater.updateById(id, newRepo);
        UpdateRepoResponseDto body = mapFromRepoToUpdateRepoResponseDto(newRepo);
        return ResponseEntity.ok(body);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<PartiallyUpdateSongResponseDto> partiallyUpdate(@PathVariable Long id, @RequestBody PartiallyUpdateRepoRequestDto request){
        GithubRepo updatedRepo = mapFromPartiallyUpdateRepoRequestDtoToRepo(request);
        GithubRepo savedRepo = repoUpdater.updatePartiallyById(id, updatedRepo);
        PartiallyUpdateSongResponseDto body = mapFromRepoToPartiallyUpdateRepoResponseDto(savedRepo);
        return ResponseEntity.ok(body);
    }
}
