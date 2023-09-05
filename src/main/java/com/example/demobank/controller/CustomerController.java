package com.example.demobank.controller;

import com.example.demobank.entity.Customer;
import com.example.demobank.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerController {
    CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @GetMapping("name-surname")
    public ResponseEntity<Customer> getCustomerByNameAndSurname(
            @RequestHeader(value = "Name") String customerName,
            @RequestHeader(value = "Surname") String customerSurname) {
        return ResponseEntity.ok(customerService.findCustomerByNameAndSurname(customerName, customerSurname));
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        return new ResponseEntity<>(customer, CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable(value = "id") Long customerId,
            @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.updateCustomer(customerId, customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCustomerById(@PathVariable(value = "id") Long customerId) {
        boolean deleted;
        deleted = customerService.deleteCustomerById(customerId);

        Map<String, Boolean> map = new HashMap<>();
        map.put("is deleted", deleted);
        return ResponseEntity.ok(map);
    }
}
