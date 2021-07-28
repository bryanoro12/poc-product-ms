package com.collabera.poc.product.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ProductCodeUtil {

    private ProductCodeUtil() {
    }

    public static String generateProductCode(final String productName) {
        final String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return date + productName;
    }
}
