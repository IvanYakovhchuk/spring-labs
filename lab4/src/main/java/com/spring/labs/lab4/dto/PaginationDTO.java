package com.spring.labs.lab4.dto;

import jakarta.validation.constraints.Min;

public class PaginationDTO {
    @Min(1)
    private int page = 1;

    @Min(1)
    private int pageSize = 10;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
