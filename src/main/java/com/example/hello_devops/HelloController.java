package com.example.hello_devops;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Hello DevOps! Welcome to my first CI/CD project.";
    }
}