package com.spring.labs.lab4.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Page<T> {
    private PaginationDTO requested;
    private int pageSize;
    private int totalPages;
    private int total;
    private List<T> items;

    public Page() {}

    public Page(PaginationDTO requested, int pageSize, int totalPages, int total, List<T> items) {
        this.requested = requested;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.total = total;
        this.items = items;
    }

    public PaginationDTO getRequested() {
        return requested;
    }

    public void setRequested(PaginationDTO requested) {
        this.requested = requested;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
