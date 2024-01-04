package com.example.demo_java_spring;

import com.example.demo_java_spring.models.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class ServiceAController {

    private final WebClient webClient;

    public ServiceAController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://nginx").build();
    }
    @GetMapping("/{sit}/service-a/api/a")
    public Mono<ResponseEntity<Response>> index(@PathVariable("sit") String sit) {
        return webClient.get()
                .uri("/{sit}/service-b/api/b", sit)
                .retrieve()
                .bodyToMono(Response.class)
                .map(response -> {
                    String message = response.getMessage();
                    String concatenatedMessage = "a" + " <- " +  message;
                    Response newResponse = new Response(concatenatedMessage);
                    return ResponseEntity.ok(newResponse);
                });
    }
}