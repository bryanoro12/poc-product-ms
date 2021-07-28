package com.collabera.poc.product.enums;

public enum Status {
    PENDING("Pending", "Your booking is in progress."),
    REJECTED("Rejected", "Your booking is rejected."),
    COMPLETED("Completed", "Your booking is completed,");

    private final String transactionStatus;
    private final String message;

    Status(final String transactionStatus, final String message) {
        this.transactionStatus = transactionStatus;
        this.message = message;
    }

    public String toLabel() {
        return transactionStatus;
    }

    public String toMessage() {
        return message;
    }
}
