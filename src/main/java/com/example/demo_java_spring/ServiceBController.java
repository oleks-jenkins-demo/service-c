package com.example.demo_java_spring;

import com.example.demo_java_spring.models.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class ServiceBController {

    private final WebClient webClient;

    public ServiceBController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://nginx").build();
    }

    @GetMapping("/{sit}/service-b/api/b")
    public Mono<ResponseEntity<Response>> index(@PathVariable("sit") String sit) {
        return webClient.get()
                .uri("/{sit}/service-c/api/c", sit)
                .retrieve()
                .bodyToMono(Response.class)
                .map(response -> {
                    String message = response.getMessage();
                    String concatenatedMessage = "b " + " <- " + message;
                    Response newResponse = new Response(concatenatedMessage);
                    return ResponseEntity.ok(newResponse);
                });
    }
}