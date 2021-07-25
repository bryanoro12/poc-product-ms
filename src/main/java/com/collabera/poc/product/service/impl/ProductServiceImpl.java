package com.collabera.poc.product.service.impl;

import com.collabera.poc.product.dto.ProductRequestDto;
import com.collabera.poc.product.entity.Product;
import com.collabera.poc.product.repository.ProductRepository;
import com.collabera.poc.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    /**
     * Save Product
     *
     * @param productRequestDto
     * @return
     */
    @Override
    public Product add(final ProductRequestDto productRequestDto) {
        log.info("Request: {}", productRequestDto.toString());
        log.info("Adding Product...");
        return productRepository.save(this.convertDtoToProduct(productRequestDto));
    }

    /**
     * Get All List of Product
     *
     * @return
     */
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    /**
     * Convert ProductDTO to Product
     *
     * @param productRequestDto
     * @return
     */
    @Override
    public Product convertDtoToProduct(final ProductRequestDto productRequestDto) {
        log.info("Converting DTO to Product...");

        final Product product = Product.builder()
            .name(productRequestDto.getName())
            .description(productRequestDto.getDescription())
            .price(Double.parseDouble(productRequestDto.getPrice()))
            .createdBy(productRequestDto.getCreatedBy())
            .build();

        log.info("Product save successfully.");
        log.info("Product: {}", product.toString());
        return product;
    }
}
