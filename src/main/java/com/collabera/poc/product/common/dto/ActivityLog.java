package com.collabera.poc.product.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityLog {
    private String action;
    private String userName;
    private String productName;
    private String productCode;
}
