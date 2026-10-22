package com.springsecuritycourse.mysecurityapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BalanceController {

    @GetMapping("/myBalance")
    public String getBalance() {
        return "This is my balance";
    }

}
