package com.anthual.springbootcustomer.customer;

public record CustomerUpdateRequest(
    String name,
    String email,
    Integer age
){}
