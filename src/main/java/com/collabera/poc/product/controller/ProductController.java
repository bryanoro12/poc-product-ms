package com.collabera.poc.product.controller;

import com.collabera.poc.product.common.dto.RequestHeaders;
import com.collabera.poc.product.dto.ProductListResponseDto;
import com.collabera.poc.product.dto.ProductRequestDto;
import com.collabera.poc.product.dto.ProductResponseDto;
import com.collabera.poc.product.entity.Product;
import com.collabera.poc.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        @RequestHeader final String requestId,
        @RequestBody final ProductRequestDto productRequestDto
    ) {
        return new ResponseEntity<>(
            new ProductResponseDto(productService.add(
                new RequestHeaders(requestId),
                productRequestDto)),
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

    /**
     * Get Single Product
     *
     * @param productCode
     * @return
     */
    @GetMapping("/{productCode}")
    public ResponseEntity<Product> getSingleProduct(
        @PathVariable final String productCode
    ) {
        return new ResponseEntity<>(
            productService.get(productCode),
            HttpStatus.OK);
    }
}
