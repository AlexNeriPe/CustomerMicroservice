package com.anthual.springbootcustomer.customer;

public record CustomerRegistrationRequest (
    String name,
    String email,
    Integer age
) {}
    