package com.spring.labs.lab4.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Paginated data")
public class Page<T> {
    @Schema(description = "Requested pagination")
    private PaginationDTO requested;

    @Schema(description = "Actual page size")
    private int pageSize;

    @Schema(description = "Total number of pages")
    private int totalPages;

    @Schema(description = "Total number of elements")
    private int total;

    @Schema(description = "Page items")
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
