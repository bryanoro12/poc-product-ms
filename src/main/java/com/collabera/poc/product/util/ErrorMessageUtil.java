package com.collabera.poc.product.util;

import com.collabera.poc.product.common.dto.ErrorMessage;

public final class ErrorMessageUtil {
    // User
    public static final ErrorMessage ERROR_400_U_INVALID_NAME  = new ErrorMessage(
        "U_001", "Invalid or missing name.");
    public static final ErrorMessage ERROR_400_U_INVALID_EMAIL  = new ErrorMessage(
        "U_002", "Invalid or missing email.");
    public static final ErrorMessage ERROR_400_U_INVALID_ADDRESS  = new ErrorMessage(
        "U_003", "Invalid or missing address.");
    public static final ErrorMessage ERROR_400_U_INVALID_EMAIL_FORMAT  = new ErrorMessage(
        "U_004", "Invalid email format.");

    // Product
    public static final ErrorMessage ERROR_400_P_INVALID_NAME  = new ErrorMessage(
        "P_001", "Invalid or missing name.");
    public static final ErrorMessage ERROR_400_P_INVALID_DESCRIPTION  = new ErrorMessage(
        "P_002", "Invalid or missing description.");
    public static final ErrorMessage ERROR_400_P_INVALID_PRICE  = new ErrorMessage(
        "P_003", "Invalid or missing price.");
    public static final ErrorMessage ERROR_400_P_INVALID_REQUEST_ID  = new ErrorMessage(
        "P_004", "Invalid or missing requestId.");
    public static final ErrorMessage ERROR_400_P_INVALID_PRICE_FORMAT  = new ErrorMessage(
        "P_005", "Invalid price format.");
    public static final ErrorMessage ERROR_400_P_DUPLICATE_REQUEST_ID  = new ErrorMessage(
        "P_006", "Duplicate requestId.");
    public static final ErrorMessage ERROR_400_P_DUPLICATE_PRODUCT_NAME  = new ErrorMessage(
        "P_007", "Duplicate product name.");
    public static final ErrorMessage ERROR_400_P_PRODUCT_CODE_DOES_NOT_EXIST  = new ErrorMessage(
        "P_008", "Product Code does not exists.");
    public static final ErrorMessage ERROR_400_P_INVALID_CREATED_BY  = new ErrorMessage(
        "P_009", "Invalid or missing createdBy.");

    // Booking
    public static final ErrorMessage ERROR_400_B_INVALID_USERNAME  = new ErrorMessage(
        "B_001", "Invalid or missing userName.");
    public static final ErrorMessage ERROR_400_B_INVALID_PRODUCT_CODE  = new ErrorMessage(
        "B_002", "Invalid or missing productCode.");
    public static final ErrorMessage ERROR_400_B_INVALID_STARTDATE  = new ErrorMessage(
        "B_003", "Invalid or missing startDate.");
    public static final ErrorMessage ERROR_400_B_INVALID_ENDDATE  = new ErrorMessage(
        "B_004", "Invalid or missing endDate.");
    public static final ErrorMessage ERROR_400_B_USERNAME_DOES_NOT_EXIST  = new ErrorMessage(
        "B_005", "userName does not exists.");
    public static final ErrorMessage ERROR_400_B_PRODUCT_CODE_DOES_NOT_EXIST  = new ErrorMessage(
        "B_006", "productCode does not exists.");
    public static final ErrorMessage ERROR_400_B_INVALID_STARTDATE_FORMAT  = new ErrorMessage(
        "B_007", "Invalid startDate format.");
    public static final ErrorMessage ERROR_400_B_INVALID_ENDATE_FORMAT  = new ErrorMessage(
        "B_008", "Invalid endDate format.");
    public static final ErrorMessage ERROR_400_B_INVALID_REQUEST_ID  = new ErrorMessage(
        "B_009", "Invalid or missing requestId.");
    public static final ErrorMessage ERROR_400_B_DUPLICATE_REQUEST_ID  = new ErrorMessage(
        "B_010", "Duplicate requestId.");

    // Global
    public static final ErrorMessage ERROR_404_G_NOT_FOUND  = new ErrorMessage(
        "G_001", "Not Found.");
    public static final ErrorMessage ERROR_405_G_METHOD_NOT_ALLOWED  = new ErrorMessage(
        "G_002", "Method not allowed.");
    public static final ErrorMessage ERROR_500_G_INTERNAL_SERVER_ERROR  = new ErrorMessage(
        "G_003", "Internal server error.");
    public static final ErrorMessage ERROR_401_G_UNAUTHORIZED  = new ErrorMessage(
        "G_004", "Unauthorized.");

    private ErrorMessageUtil() {
    }
}
