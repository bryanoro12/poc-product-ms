package com.collabera.poc.product.controller;

import com.collabera.poc.product.dto.ProductListResponseDto;
import com.collabera.poc.product.dto.ProductRequestDto;
import com.collabera.poc.product.dto.ProductResponseDto;
import com.collabera.poc.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Create Product
     *
     * @param productRequestDto
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<ProductResponseDto> addProduct(
        @RequestBody final ProductRequestDto productRequestDto
    ) {
        return new ResponseEntity<>(
            new ProductResponseDto(productService.add(productRequestDto)),
            HttpStatus.CREATED);
    }

    /**
     * Get All Product
     *
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<ProductListResponseDto> getAllProducts() {
        return new ResponseEntity<>(
            new ProductListResponseDto(productService.getAll()),
            HttpStatus.OK);
    }
}
