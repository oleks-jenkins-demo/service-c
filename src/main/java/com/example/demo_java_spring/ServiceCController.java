package com.example.demo_java_spring;

import com.example.demo_java_spring.models.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceCController {

    @GetMapping("/{sit}/service-c/api/c")
    public ResponseEntity<Response> index(@PathVariable("sit") String sit) {
        Response response = new Response("c");
        return ResponseEntity.ok(response);
    }
}