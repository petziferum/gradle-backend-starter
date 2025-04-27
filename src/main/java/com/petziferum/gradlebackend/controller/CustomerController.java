package com.petziferum.gradlebackend.controller;


import com.petziferum.gradlebackend.models.Customer;
import com.petziferum.gradlebackend.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/save")
    public ResponseEntity<Customer> saveNewCustomer(@RequestBody Customer customer) {
        customer.setId(null);
        log.info("id auf {} gesetzt", customer.getId());

        return ResponseEntity.ok(customerRepository.save(customer));
    }

    @GetMapping("all")
    public ResponseEntity<Iterable<Customer>> getCustomer() {
        Iterable<Customer> list = customerRepository.findAll();
        return ResponseEntity.ok(list);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer updatedCustomer) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setFirstName(updatedCustomer.getFirstName());
                    customer.setLastName(updatedCustomer.getLastName());
                    customer.setEmail(updatedCustomer.getEmail());
                    customer.setDescription(updatedCustomer.getDescription());
                    customer.setHaarfarbe(updatedCustomer.getHaarfarbe());
                    customer.setAugenfarbe(updatedCustomer.getAugenfarbe());
                    customer.setGewicht(updatedCustomer.getGewicht());
                    customer.setGroesse(updatedCustomer.getGroesse());

                    Customer savedCustomer = customerRepository.save(customer);
                    return ResponseEntity.ok(savedCustomer);
                })
                .orElseGet(() -> {
                    return ResponseEntity.notFound().build();
                });
    }
}
