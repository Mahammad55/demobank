package com.example.demobank.service;

import com.example.demobank.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer getCustomerById(Long id);

    List<Customer> getCustomers();

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(Long id, Customer customer);

    boolean deleteCustomerById(Long id);

    Customer findCustomerByNameAndSurname(String name, String surname);
}
