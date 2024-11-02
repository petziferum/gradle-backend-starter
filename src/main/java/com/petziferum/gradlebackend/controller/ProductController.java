package com.petziferum.gradlebackend.controller;


import com.petziferum.gradlebackend.models.Product;
import com.petziferum.gradlebackend.models.ProductRequest;
import com.petziferum.gradlebackend.repository.ProductRepository;
import com.petziferum.gradlebackend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.HashMap;;
import java.util.Map;
import java.util.Optional;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController implements ProductControllerAnnotations {


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
            Optional<Product> newProduct = productRepo.findById(savedProduct.getId());
            if(newProduct.isPresent()) {
                return ResponseEntity.ok(savedProduct);
            } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Error saving product {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Map<String, Object>> getAllProducts(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Product> pagedProducts = productRepo.findAll(paging);
        Map<String, Object> response = new HashMap<>();

        if (pagedProducts.isEmpty()) {
            response.put("status", "Leer");
            response.put("message", "Das Produktlager ist komplett leer!");
            response.put("content", Collections.emptyList());
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "Erfolg");
            response.put("message", "Produkte werden ausgegeben.");
            response.put("currentPage", pagedProducts.getNumber());
            response.put("totalItems", pagedProducts.getTotalElements());
            response.put("totalPages", pagedProducts.getTotalPages());
            response.put("content", pagedProducts.getContent());
            return ResponseEntity.ok(response);
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
