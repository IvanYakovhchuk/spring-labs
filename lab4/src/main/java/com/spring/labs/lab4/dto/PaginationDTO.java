package com.spring.labs.lab4.dto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;

public class PaginationDTO {
    @Parameter(description = "Page number to return")
    @Min(1)
    private int page = 1;

    @Parameter(description = "Number of elements on a page")
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
