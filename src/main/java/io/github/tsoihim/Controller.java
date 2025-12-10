package io.github.tsoihim;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import jakarta.inject.Inject;
import reactor.core.publisher.Mono;

/**
 * An example of a controller which uses {@link WebClient} inside.
 */
@RestController
public class Controller {
    private final WebClient webClient;

    @Inject
    public Controller(Builder builder,
                           @Value("${server.port}") int port) {
        this(builder.baseUrl("http://127.0.0.1:" + port).build());
    }

    Controller(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/")
    Mono<String> index() {
        return webClient.get()
                .uri("/hello")
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping("/hello")
    String hello() {
        return "Hello, World";
    }
}