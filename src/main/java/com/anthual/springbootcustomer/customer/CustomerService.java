package com.anthual.springbootcustomer.customer;

import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.anthual.springbootcustomer.exception.DuplicateResourceException;
import com.anthual.springbootcustomer.exception.RequestValidationException;
import com.anthual.springbootcustomer.exception.ResourceNotFoundException;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    public CustomerService(@Qualifier("jpa") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers() {
        return customerDao.selectAllCustomers();
    }

    public Customer getCustomer(Integer customerId) {
        return customerDao.getCustomerById(customerId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("customer with id [%s] not found".formatted(customerId)));
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        if (customerDao.existPersonWithEmail(customerRegistrationRequest.email())) {
            throw new DuplicateResourceException("email already taken");
        }
        Customer customer = new Customer(
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                customerRegistrationRequest.age());

        customerDao.insertCustomer(customer);

    }

    public void deleteCustomerById(Integer customerId) {
        if (!customerDao.existPersonWithCustomerId(customerId)) {
            throw new ResourceNotFoundException("customer not found");
        }
        customerDao.deleteCustomerById(customerId);
    }

    public void updateCustomer(Integer customerId, CustomerUpdateRequest updateRequest) {
        Customer customer = getCustomer(customerId);

        boolean changes = false;

        if (updateRequest.name() != null && !updateRequest.name().equals(customer.getName())) {
            customer.setName(updateRequest.name());
            changes = true;
        }

        if (updateRequest.age() != null && !updateRequest.age().equals(customer.getAge())) {
            customer.setAge(updateRequest.age());
            changes = true;
        }

        if (updateRequest.email() != null && !updateRequest.email().equals(customer.getEmail())) {
            if (customerDao.existPersonWithEmail(updateRequest.email())) {
                throw new DuplicateResourceException("email already taken");
            }
            customer.setEmail(updateRequest.email());
            changes = true;
        }
        if (!changes) {
            throw new RequestValidationException("email already taken");
        }

        customerDao.updateCustomer(customer);
    }

}
