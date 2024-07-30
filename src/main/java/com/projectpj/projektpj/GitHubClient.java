package com.projectpj.projektpj;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class GitHubClient {
    private final WebClient webClient;

    public GitHubClient(WebClient.Builder webClientBuilder, @Value("${github.api.url}") String githubApiUrl) {
        this.webClient = webClientBuilder.baseUrl(githubApiUrl).build();
    }

    public Mono<List<Repository>> getRepositories(String username) {
        return webClient.get()
                .uri("/users/{username}/repos", username)
                .retrieve()
                .bodyToFlux(Repository.class)
                .filter(repo -> !repo.isFork())
                .collectList();
    }

    public Mono<List<Branch>> getBranches(String owner, String repo) {
        return webClient.get()
                .uri("/repos/{owner}/{repo}/branches", owner, repo)
                .retrieve()
                .bodyToFlux(Branch.class)
                .collectList();
    }
}
