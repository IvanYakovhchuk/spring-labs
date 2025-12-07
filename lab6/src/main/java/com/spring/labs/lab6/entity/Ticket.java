package com.spring.labs.lab6.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket")
@NamedQuery(
        name = "Ticket.findTicketByIdUsingNamedQueryAnnotation",
        query = "SELECT t FROM Ticket t WHERE t.id = :id"
)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "screening_id")
    private Long screeningId;
    @Column(name = "seat_id")
    private Long seatId;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "price")
    private double price;

    public Ticket(Long id, Long screeningId, Long seatId, String customerName, double price) {
        this.id = id;
        this.screeningId = screeningId;
        this.seatId = seatId;
        this.customerName = customerName;
        this.price = price;
    }

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(Long screeningId) {
        this.screeningId = screeningId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", screeningId=" + screeningId +
                ", seatId=" + seatId +
                ", customerName='" + customerName + '\'' +
                ", price=" + price +
                '}';
    }
}
