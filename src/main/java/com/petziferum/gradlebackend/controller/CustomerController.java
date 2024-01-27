package com.petziferum.gradlebackend.controller;


import com.petziferum.gradlebackend.models.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {


    @PostMapping("/save")
    public ResponseEntity<Customer> saveNewCustomer(@RequestBody Customer customer) {
        customer.setId(null);
        log.info("id auf {} gesetzt", customer.getId());
        return ResponseEntity.ok(customer);
    }

    @GetMapping("all")
    public ResponseEntity<Iterable<Customer>> getCustomer() {
        Iterable<Customer> list = new ArrayList<>();
        Customer c1 = new Customer(1, "Customer 1");
        ((ArrayList<Customer>) list).add(c1);
        return ResponseEntity.ok(list);
    }
}
