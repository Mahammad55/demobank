package com.example.demobank.controller;

import com.example.demobank.entity.Customer;
import com.example.demobank.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getCustomers(){
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @PostMapping("create")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
        return ResponseEntity.ok(customer);
    }
}
