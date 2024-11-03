package com.petziferum.gradlebackend.service;

import com.petziferum.gradlebackend.models.Customer;
import com.petziferum.gradlebackend.models.Product;
import com.petziferum.gradlebackend.models.ProductRequest;
import com.petziferum.gradlebackend.models.ProductResponse;
import com.petziferum.gradlebackend.repository.CustomerRepository;
import com.petziferum.gradlebackend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Slf4j
@Service
public class ProductService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public ProductService(CustomerRepository customerRepo, ProductRepository productRepository) {
        this.customerRepository = customerRepo;
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductRequest productRequest) {
        Customer owner = null;
        if(productRequest.getOwner() != null) {
            owner = customerRepository.findById(productRequest.getOwner())
                    .orElse(null);
        }
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .tags(productRequest.getTags())
                .categories(productRequest.getCategories())
                .owner(owner)
                .build();
        Product p = productRepository.save(product);
        log.info("Product created {}", product.getId());
        return p;
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Product p1 = Product.builder()
                .id("1")
                .name("Product 1")
                .description("Product 1 description")
                .price(100)
                .build();
        products.add(p1);
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .status("")
                .message("")
                .content(new ArrayList<>(Arrays.asList(
                        Product.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .description(product.getDescription())
                                .price(product.getPrice())
                                .build())))
                .build();
    }
}
