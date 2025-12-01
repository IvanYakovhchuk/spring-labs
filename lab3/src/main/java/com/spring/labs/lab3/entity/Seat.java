package com.spring.labs.lab3.entity;

public class Seat {

    private Long id;
    private int cinemaHall;
    private int row;
    private int number;
    private boolean vip;

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
