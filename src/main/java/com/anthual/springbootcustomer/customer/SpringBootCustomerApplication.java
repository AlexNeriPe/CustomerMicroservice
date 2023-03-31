package com.anthual.springbootcustomer.customer;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
// @RestController
// @RequestMapping("api/v1/customers")
public class SpringBootCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCustomerApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CustomerRepository customerRepository) {
		return args -> {
			Customer alex = new Customer(1,
					"Alex",
					"alex@mail.com",
					29);
			Customer anthu = new Customer(2,
					"anthu",
					"anthu@mail.com",
					29);
			List<Customer> customers = List.of(alex, anthu);
			customerRepository.saveAll(customers);
		};
	}

}
