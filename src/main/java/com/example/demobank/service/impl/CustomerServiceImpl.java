package com.example.demobank.service.impl;

import com.example.demobank.entity.Customer;
import com.example.demobank.exception.CustomerNotFoundException;
import com.example.demobank.repository.CustomerRepository;
import com.example.demobank.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id = %s not found!".formatted(id)));
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer newCustomer = getCustomerById(id);

        newCustomer.setName(customer.getName());
        newCustomer.setSurname(customer.getSurname());
        newCustomer.setCif(customer.getCif());
        newCustomer.setIdNumber(customer.getIdNumber());

        return customerRepository.save(newCustomer);
    }

    @Override
    public boolean deleteCustomerById(Long id) {
        getCustomerById(id);
        customerRepository.deleteById(id);
        return true;
    }

    @Override
    public Customer findCustomerByNameAndSurname(String name, String surname) {
        return customerRepository.findCustomerByNameAndSurname(name, surname)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with name =%s and surname =%s not found".formatted(name, surname)));
    }
}
