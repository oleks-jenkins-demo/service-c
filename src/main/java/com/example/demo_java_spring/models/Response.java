package com.example.demo_java_spring.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    @JsonProperty
    private String message;

    public Response() {
    }

    public Response(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
