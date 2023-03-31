package com.anthual.springbootcustomer.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List<Customer> selectAllCustomers();
    Optional<Customer> getCustomerById(Integer customerId);
    void insertCustomer(Customer customer);
    boolean existPersonWithEmail(String email);
    boolean existPersonWithCustomerId(Integer customerId);
    void deleteCustomerById(Integer customerId);
    void updateCustomer(Customer update);
}
