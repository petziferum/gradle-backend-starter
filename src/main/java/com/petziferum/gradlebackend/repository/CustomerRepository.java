package com.petziferum.gradlebackend.repository;


import com.petziferum.gradlebackend.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
