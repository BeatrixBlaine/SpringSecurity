package com.springsecuritycourse.mysecurityapp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String email;

    private String mobile_number;

    private String pwd;

    private String role;

    private LocalDate create_dt;

    public Customer() {}

    public Customer(String name, String email, String mobile_number, String pwd, String role, LocalDate create_dt) {
        this.name = name;
        this.email = email;
        this.mobile_number = mobile_number;
        this.pwd = pwd;
        this.role = role;
        this.create_dt = create_dt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getCreate_dt() {
        return create_dt;
    }

    public void setCreate_dt(LocalDate create_dt) {
        this.create_dt = create_dt;
    }
}
