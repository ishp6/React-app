package com.example.backend;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class MessageController {

    @GetMapping("/message")
    public Map<String, String> getMessage() {
        return Map.of("message", "Hello from the Java backend!");
    }
}
