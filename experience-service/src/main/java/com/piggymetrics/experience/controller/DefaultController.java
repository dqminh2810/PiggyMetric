package com.piggymetrics.experience.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
    @GetMapping("/")
    public String welcome(){
        return "<h1>Welcome experience</h1>";
    }
}
