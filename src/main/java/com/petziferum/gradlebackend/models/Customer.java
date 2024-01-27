package com.petziferum.gradlebackend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer {

    String id;
    String name;
    public Customer(Integer id, String name) {
    }
}
