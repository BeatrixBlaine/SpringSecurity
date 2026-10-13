package com.springsecuritycourse.mysecurityapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoRestController {

    @GetMapping("/test")
    public String helloWorld() {
        return "Hello world!";
    }

}
