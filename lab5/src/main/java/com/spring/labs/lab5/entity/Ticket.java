package com.spring.labs.lab5.entity;

import java.math.BigDecimal;

public class Ticket {
    private long id;
    private long screeningId;
    private long seatId;
    private String customerName;
    private BigDecimal price;

    public Ticket() {}

    public Ticket(long id, long seatId, long screeningId, String customerName, BigDecimal price) {
        this.id = id;
        this.seatId = seatId;
        this.screeningId = screeningId;
        this.customerName = customerName;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(long screeningId) {
        this.screeningId = screeningId;
    }

    public long getSeatId() {
        return seatId;
    }

    public void setSeatId(long seatId) {
        this.seatId = seatId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
