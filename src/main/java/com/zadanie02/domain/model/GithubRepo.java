package com.zadanie02.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Entity
@Getter
@Setter
@Table(name = "repo")
public class GithubRepo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(nullable = false)
    String owner;

    @Column(nullable = false)
    String name;

    public GithubRepo() {
    }

    public GithubRepo(String owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public GithubRepo(Long id, String owner, String name) {
        this.id = id;
        this.owner = owner;
        this.name = name;
    }
}
