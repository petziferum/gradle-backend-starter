package com.petziferum.gradlebackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;
    private String description;
    private Integer price;

    @ElementCollection
    @CollectionTable(name = "product_tags")
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "product_categories")
    private List<String> categories;

    public Product addCategory(String category) {
        this.categories.add(category);
        return this;
    }

}
