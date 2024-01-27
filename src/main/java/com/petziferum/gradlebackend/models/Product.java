package com.petziferum.gradlebackend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {

    private String id;
    private String name;
    private String description;
    private Integer price;


}
