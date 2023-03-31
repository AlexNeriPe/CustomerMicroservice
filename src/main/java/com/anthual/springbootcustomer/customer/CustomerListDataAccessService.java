package com.anthual.springbootcustomer.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDao {

    private static final List<Customer> customers;

    static {
        customers = new ArrayList<>();
        Customer alex = new Customer(1, "Alex", "alex@mail.comn", 29);
        customers.add(alex);
        Customer anthu = new Customer(2, "anthu", "anthu@mail.com", 29);
        customers.add(anthu);
    };

    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> getCustomerById(Integer customerId) {
        return customers.stream()
                .filter(c -> c.getId().equals(customerId))
                .findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existPersonWithEmail(String email) {
        return customers.stream()
                        .anyMatch(c -> c.getEmail().equals(email));
    }

    @Override
    public boolean existPersonWithCustomerId(Integer customerId) {
        return customers.stream()
                        .anyMatch(c -> c.getId().equals(customerId));
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        customers.stream()
                .filter(c -> c.getId().equals(customerId))
                .findFirst()
                .ifPresent(customers::remove);
    }

    @Override
    public void updateCustomer(Customer update) {
        customers.add(update);
    }

}
