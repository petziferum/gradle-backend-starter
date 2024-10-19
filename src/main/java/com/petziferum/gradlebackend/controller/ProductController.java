package com.petziferum.gradlebackend.controller;


import com.petziferum.gradlebackend.models.Customer;
import com.petziferum.gradlebackend.models.Product;
import com.petziferum.gradlebackend.models.ProductRequest;
import com.petziferum.gradlebackend.models.ProductResponse;
import com.petziferum.gradlebackend.repository.ProductRepository;
import com.petziferum.gradlebackend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;

    @Autowired
    ProductRepository productRepo;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> saveNewProduct(@RequestBody ProductRequest product) {
        log.debug("############product {}", product);
        try {
            Product p = productService.createProduct(product);
            Product savedProduct = productRepo.save(p);
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            log.error("Error saving product {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get all products", description = "Get all products")
    @GetMapping("all")
    public ResponseEntity<List<Product>> getProduct() {
        List<Product> productList = productRepo.findAll();
        if(productList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(productList);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product updatedProduct) {
        return productRepo.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setDescription(updatedProduct.getDescription());
                    product.setPrice(updatedProduct.getPrice());
                    product.setOwner(updatedProduct.getOwner());

                    Product savedProduct = productRepo.save(product);
                    return ResponseEntity.ok(savedProduct);
                })
                .orElseGet(() -> {
                    return ResponseEntity.notFound().build();
                });
    }
}
