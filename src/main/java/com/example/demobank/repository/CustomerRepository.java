package com.example.demobank.repository;

import com.example.demobank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByNameAndSurname(String name, String surname);
}
