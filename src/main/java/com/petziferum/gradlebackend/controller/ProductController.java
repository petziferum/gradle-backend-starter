package com.petziferum.gradlebackend.controller;


import com.petziferum.gradlebackend.models.Product;
import com.petziferum.gradlebackend.models.ProductRequest;
import com.petziferum.gradlebackend.models.ProductResponse;
import com.petziferum.gradlebackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> saveNewProduct(@RequestBody ProductRequest product) {
        log.debug("############product {}", product);
        Product p = productService.createProduct(product);
        return ResponseEntity.ok(p);
    }

    @GetMapping("all")
    public ResponseEntity<List<ProductResponse>> getCustomer() {
        List<ProductResponse> ps = productService.getAllProducts();
        return ResponseEntity.ok(ps);
    }
}
