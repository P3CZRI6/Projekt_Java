package com.projectpj.projektpj;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GitHubController {
    private final GitHubClient gitHubClient;

    @GetMapping("/users/{username}/repositories")
    public Mono<ResponseEntity<Object>> getRepositories(@PathVariable String username) {
        return gitHubClient.getRepositories(username)
                .flatMapMany(Flux::fromIterable)
                .flatMap(repo -> gitHubClient.getBranches(repo.getOwner().getLogin(), repo.getName())
                        .map(branches -> {
                            repo.setBranches(branches);
                            return repo;
                        }))
                .collectList()
                .map(repos -> ResponseEntity.ok((Object) repos)) 
                .onErrorResume(WebClientResponseException.NotFound.class, ex -> 
                    Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(createErrorResponse(404, "User not found")))
                );
    }

    private ErrorResponse createErrorResponse(int status, String message) {
        return new ErrorResponse(status, message);
    }

    @Data
    @AllArgsConstructor
    public static class ErrorResponse {
        private int status;
        private String message;
    }
}
