package com.example.demobank.controller;

import com.example.demobank.entity.Customer;
import com.example.demobank.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerController {
    CustomerService customerService;

    @GetMapping("customerId/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping("all")
    public ResponseEntity<?> getCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @GetMapping("name-surname")
    public ResponseEntity<?> getCustomerByNameAndSurname(@RequestHeader("Name") String name, @RequestHeader("Surname") String surname) {
        return ResponseEntity.ok(customerService.findCustomerByNameAndSurname(name, surname));
    }

    @PostMapping("create")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        customer.setActive(1);
        customerService.saveCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("customerId/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Long id) {
        boolean deleted = false;
        deleted = customerService.deleteCustomerById(id);

        Map<String, Boolean> map = new HashMap<>();
        map.put("is deleted", deleted);
        return ResponseEntity.ok(map);
    }
}
