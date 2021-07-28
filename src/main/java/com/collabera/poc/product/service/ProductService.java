package com.collabera.poc.product.service;

import com.collabera.poc.product.common.dto.RequestHeaders;
import com.collabera.poc.product.dto.ProductRequestDto;
import com.collabera.poc.product.entity.Product;

import java.util.List;

public interface ProductService {
    Product add(RequestHeaders requestHeaders, ProductRequestDto productRequestDto);

    List<Product> getAll();

    Product get(String productCode);

    Product convertDtoToProduct(RequestHeaders requestHeaders, ProductRequestDto productRequestDto);
}
