package com.petziferum.gradlebackend.controller;

import com.petziferum.gradlebackend.models.Product;
import com.petziferum.gradlebackend.models.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface ProductControllerAnnotations {

    @Operation(summary = "Get all products", description = "Get all products")
    @ApiResponse(responseCode = "200",
    description = "Prdukte werden ausgegeben.",
    content = @Content(array = @ArraySchema(schema = @Schema( implementation = ProductResponse.class))))
    @GetMapping("all")
    ResponseEntity<Map<String, Object>> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size);
}
