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
    @ManyToOne
    @JoinColumn(name = "screening_id")
    private MovieScreening screening;
    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "price")
    private double price;

    public Ticket(Long id, Seat seat, MovieScreening screening, String customerName, double price) {
        this.id = id;
        this.seat = seat;
        this.screening = screening;
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

    public MovieScreening getScreening() {
        return screening;
    }

    public void setScreening(MovieScreening screening) {
        this.screening = screening;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
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
                ", screening=" + screening +
                ", seat=" + seat +
                ", customerName='" + customerName + '\'' +
                ", price=" + price +
                '}';
    }
}
