package com.springsecuritycourse.mysecurityapp.controller;

import com.springsecuritycourse.mysecurityapp.entity.Customer;
import com.springsecuritycourse.mysecurityapp.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final CustomerService customerService;

    public UserController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {

        try {

            Customer savedCustomer = customerService.save(customer);

            if(savedCustomer.getId() > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("User successfully registered");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed");
            }

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }

    }

}
