package io.github.tsoihim;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class DeviceController {
    @GetMapping("/")
    Mono<String> index() {
        return null;
    }

    @GetMapping("/hello")
    String hello() {
        return null;
    }
}