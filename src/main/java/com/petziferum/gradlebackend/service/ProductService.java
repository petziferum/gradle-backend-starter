package com.petziferum.gradlebackend.service;

import com.petziferum.gradlebackend.models.Product;
import com.petziferum.gradlebackend.models.ProductRequest;
import com.petziferum.gradlebackend.models.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    public Product createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .tags(productRequest.getTags())
                .categories(productRequest.getCategories())
                .build();
        log.info("Product created {}", product.getId());
        return product;
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
