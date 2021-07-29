package com.collabera.poc.product.service.impl;

import com.collabera.poc.product.common.dto.ActivityLog;
import com.collabera.poc.product.common.dto.RequestHeaders;
import com.collabera.poc.product.dto.ProductRequestDto;
import com.collabera.poc.product.entity.Product;
import com.collabera.poc.product.enums.Action;
import com.collabera.poc.product.exception.ProductNotFoundException;
import com.collabera.poc.product.repository.ProductRepository;
import com.collabera.poc.product.service.ActivityLogService;
import com.collabera.poc.product.service.KafkaService;
import com.collabera.poc.product.service.ProductService;
import com.collabera.poc.product.service.ProductValidationService;
import com.collabera.poc.product.util.ErrorMessageUtil;
import com.collabera.poc.product.util.ProductCodeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductValidationService productValidationService;
    private final ProductRepository productRepository;
    private final ActivityLogService activityLogService;
    private final KafkaService kafkaService;

    /**
     * Save Product
     *
     * @param requestHeaders
     * @param productRequestDto
     * @return
     */
    @Override
    public Product add(
        final RequestHeaders requestHeaders,
        final ProductRequestDto productRequestDto
    ) {
        log.info("Request: {}", productRequestDto.toString());
        productValidationService.validateRequestHeader(requestHeaders);
        productValidationService.validateRequestBody(productRequestDto);

        log.info("Adding Product...");
        final Product product = productRepository.save(this.convertDtoToProduct(
            requestHeaders,
            productRequestDto));

        log.info("Product save successfully.");
        log.info("Product: {}", product.toString());

        final String message = activityLogService.convertActivityLogToString(
            ActivityLog.builder()
                .action(Action.ADD.toLabel())
                .userName(product.getCreatedBy())
                .productName(product.getName())
                .productCode(product.getProductCode())
                .build());

        log.info("Message {}", message);
        kafkaService.sendMessage(message);
        return product;
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
     * Get Single Product
     *
     * @param productCode
     * @return
     */
    @Override
    public Product get(final String productCode) {
        return productRepository
            .findByProductCode(productCode)
            .orElseThrow(() -> new ProductNotFoundException(
                ErrorMessageUtil.ERROR_400_P_PRODUCT_CODE_DOES_NOT_EXIST));
    }

    /**
     * Convert ProductDTO to Product
     *
     * @param requestHeaders
     * @param productRequestDto
     * @return
     */
    @Override
    public Product convertDtoToProduct(
        final RequestHeaders requestHeaders,
        final ProductRequestDto productRequestDto
    ) {
        log.info("Converting DTO to Product...");

        return Product.builder()
            .requestId(requestHeaders.getRequestId())
            .productCode(ProductCodeUtil.generateProductCode(productRequestDto.getName()))
            .name(productRequestDto.getName())
            .description(productRequestDto.getDescription())
            .price(Double.parseDouble(productRequestDto.getPrice()))
            .createdBy(productRequestDto.getCreatedBy())
            .build();
    }
}
