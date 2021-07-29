package com.collabera.poc.product.enums;

public enum Action {
    ADD("Add Product"),
    BOOK("Book Product");

    private final String userAction;

    Action(final String userAction) {
        this.userAction = userAction;
    }

    public String toLabel() {
        return userAction;
    }
}
