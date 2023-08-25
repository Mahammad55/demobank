package com.example.demobank.service;

import com.example.demobank.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer getCustomerById(Long id);

    List<Customer> getCustomers();

    Customer saveCustomer(Customer customer);
}
