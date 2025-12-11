package com.spring.labs.lab5.entity;

public class Seat {
    private long id;
    private int cinemaHall;
    private int rowNumber;
    private int seatNumber;
    private boolean isVip;

    public Seat() {}

    public Seat(long id, int cinemaHall, int rowNumber, int seatNumber, boolean isVip) {
        this.id = id;
        this.cinemaHall = cinemaHall;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.isVip = isVip;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(int cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean getIsVip() {
        return isVip;
    }

    public void setIsVip(boolean vip) {
        isVip = vip;
    }
}
