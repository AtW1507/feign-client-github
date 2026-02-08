package com.zadanie02.domain.repository;


import com.zadanie02.domain.model.GithubRepo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.Repository;


import java.util.List;
import java.util.Optional;

public interface GithubRepository extends Repository<GithubRepo, Long> {

    GithubRepo save(GithubRepo githubRepo);

    @Query("SELECT s FROM GithubRepo s")
    List<GithubRepo> findAll(Pageable pageable);

    @Query("SELECT s FROM GithubRepo s WHERE s.id = :id")
    Optional<GithubRepo> findById(Long id);

    @Query("SELECT s FROM GithubRepo s WHERE s.owner = :owner AND s.name = :name")
    Optional<GithubRepo> findByOwnerAndName(String owner, String name);

    @Modifying
    @Query("DELETE FROM GithubRepo s WHERE s.id = :id")
    void deleteById(Long id);

    @Modifying
    @Query("UPDATE GithubRepo s SET s.name = :#{#newRecord.name}, s.owner = :#{#newRecord.owner} WHERE s.id = :id")
    void updateById(Long id, GithubRepo newRecord);

    boolean existsById(Long id);
}
