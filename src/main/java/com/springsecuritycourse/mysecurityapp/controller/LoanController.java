package com.springsecuritycourse.mysecurityapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoanController {

    @GetMapping("/myLoans")
    public String getLoan() {
        return "This is my loans";
    }

}
