package com.spring.labs.lab6.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seat")
@NamedQuery(
        name = "Seat.findSeatByIdUsingNamedQueryAnnotation",
        query = "SELECT s FROM Seat s WHERE s.id = :id"
)
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "cinema_hall")
    private int cinemaHall;
    @Column(name = "row")
    private int row;
    @Column(name = "number")
    private int number;
    @Column(name = "vip")
    private boolean vip;
    @OneToMany(mappedBy = "seat", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    public Seat() {
    }

    public Seat(Long id, int cinemaHall, int row, int number, boolean vip) {
        this.id = id;
        this.cinemaHall = cinemaHall;
        this.row = row;
        this.number = number;
        this.vip = vip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(int cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", cinemaHall=" + cinemaHall +
                ", row=" + row +
                ", number=" + number +
                ", vip=" + vip +
                '}';
    }
}
