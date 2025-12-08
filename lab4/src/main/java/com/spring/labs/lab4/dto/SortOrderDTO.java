package com.spring.labs.lab4.dto;

public class SortOrderDTO {
    private String orderBy = "id";
    private SortOrder order = SortOrder.ASC;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public SortOrder getOrder() {
        return order;
    }

    public void setOrder(SortOrder order) {
        this.order = order;
    }
}
