package com.zadanie02.infrastructure.controller;

import com.zadanie02.domain.model.GithubRepo;
import com.zadanie02.infrastructure.controller.dto.requestDatabase.CreateRepoRequestDto;
import com.zadanie02.infrastructure.controller.dto.requestDatabase.PartiallyUpdateRepoRequestDto;
import com.zadanie02.infrastructure.controller.dto.requestDatabase.UpdateRepoRequestDto;
import com.zadanie02.infrastructure.controller.dto.response.responseDatabase.*;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.util.List;



public class RepoMapper {

    public static @NonNull GithubRepo mapFromCreateRepoRequestDtoToRepo(CreateRepoRequestDto dto){
        GithubRepo repo = new GithubRepo(dto.owner(), dto.name());
        return repo;
    }
    public static RepoDto mapFromRepoToRepoDto(GithubRepo repo){
        return new RepoDto(repo.getId(), repo.getOwner(), repo.getName());
    }
    public static @NonNull CreateRepoResponseDto mapFromRepoToCreateRepoRequestDto(GithubRepo repo){
        RepoDto repoDto = RepoMapper.mapFromRepoToRepoDto(repo);
        return new CreateRepoResponseDto(repoDto);
    }
    public static DeleteRepoResponseDto mapFromRepoToDeleteRepoResponseDto(Long id){
        return new DeleteRepoResponseDto("You deleted repo with id: " + id, HttpStatus.OK);
    }
    public static UpdateRepoResponseDto mapFromRepoToUpdateRepoResponseDto(GithubRepo newRecord){
        return new UpdateRepoResponseDto(newRecord.getName(), newRecord.getOwner());
    }
    public static PartiallyUpdateSongResponseDto mapFromRepoToPartiallyUpdateRepoResponseDto(GithubRepo repo){
        RepoDto repoDto = RepoMapper.mapFromRepoToRepoDto(repo);
        return new PartiallyUpdateSongResponseDto(repoDto);
    }
    public static GetRepoResponseDto mapFromRepoTOGetRepoResponseDto(GithubRepo repo){
        RepoDto repoDto = RepoMapper.mapFromRepoToRepoDto(repo);
        return new GetRepoResponseDto(repoDto);
    }
    public static GithubRepo mapFromPartiallyUpdateRepoRequestDtoToRepo(PartiallyUpdateRepoRequestDto dto){
        return  new GithubRepo(dto.name(), dto.owner());
    }
    public static GetAllRepoResponseDto mapFromRepoToGetAllReposResponseDto(List<GithubRepo> repos){
        List<RepoDto> repoDtos = repos.stream().map(RepoMapper::mapFromRepoToRepoDto).toList();
        return new GetAllRepoResponseDto(repoDtos);
    }
    public static GithubRepo mapFromUpdateRepoRequestDtoToRepo(UpdateRepoRequestDto dto){
        return new GithubRepo(dto.owner(), dto.name());
    }
}
