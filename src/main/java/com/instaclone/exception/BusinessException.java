package com.instaclone.exception;

import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class BadRequestException {

    private final String message;

    public BadRequestException(String message) {
        throw new 
    }

    public String getMessage() {
        return message;
    }

    private static Map<String, Object> getAlertParameters(String message) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", message);
        parameters.put("status", 404);
        return parameters;
    }
}
