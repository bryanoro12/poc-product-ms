package com.collabera.poc.product.controller;

import com.collabera.poc.product.common.dto.RequestHeaders;
import com.collabera.poc.product.dto.ProductListResponseDto;
import com.collabera.poc.product.dto.ProductRequestDto;
import com.collabera.poc.product.dto.ProductResponseDto;
import com.collabera.poc.product.entity.Product;
import com.collabera.poc.product.service.ProductService;
import com.collabera.poc.product.util.ResponseCodesUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
     * @param requestId
     * @param productRequestDto
     * @return
     */
    @PostMapping("/")
    @ApiOperation(value = "Add Product")
    @ApiResponses(value = {
        @ApiResponse(code = ResponseCodesUtil.BAD_REQUEST, message = "Invalid request parameter"),
        @ApiResponse(code = ResponseCodesUtil.CREATED, message = "Successful add product")})
    public ResponseEntity<ProductResponseDto> addProduct(
        @ApiParam(
            required = true,
            example = "0de4be40-48a2-4f45-89b9-9f8a6a05791a")
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
    @ApiOperation(value = "Get All Product")
    @ApiResponse(code = ResponseCodesUtil.OK, message = "Success get all product")
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
    @ApiOperation(value = "Get Single Product")
    @ApiResponses(value = {
        @ApiResponse(code = ResponseCodesUtil.BAD_REQUEST, message = "Invalid request parameter"),
        @ApiResponse(code = ResponseCodesUtil.CREATED, message = "Successful get single product")})
    public ResponseEntity<Product> getSingleProduct(
        @ApiParam(
            required = true,
            example = "20210727000951HONDA_CIVIC")
        @PathVariable final String productCode
    ) {
        return new ResponseEntity<>(
            productService.get(productCode),
            HttpStatus.OK);
    }
}
