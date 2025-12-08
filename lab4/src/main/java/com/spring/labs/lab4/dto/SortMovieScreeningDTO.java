package com.spring.labs.lab4.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

public class SortMovieScreeningDTO {
    @Parameter(
            description = "Field to sort by",
            schema = @Schema(
                    type = "string",
                    allowableValues = {"id", "date", "movieName", "cinemaHall"},
                    defaultValue = "id"
            )
    )
    private String orderBy = "id";

    @Parameter(description = "Sort order")
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
