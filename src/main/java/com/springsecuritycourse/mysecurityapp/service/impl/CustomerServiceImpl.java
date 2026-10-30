package com.springsecuritycourse.mysecurityapp.service.impl;

import com.springsecuritycourse.mysecurityapp.entity.Customer;
import com.springsecuritycourse.mysecurityapp.repository.CustomerRepository;
import com.springsecuritycourse.mysecurityapp.service.CustomerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Customer save(Customer customer) {

        String hashedPassword = passwordEncoder.encode(customer.getPwd());
        customer.setPwd(hashedPassword);

        return customerRepository.save(customer);
    }
}
