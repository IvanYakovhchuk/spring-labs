package com.spring.labs.lab5.entity;

public class Seat {
    private long id;
    private long cinemaHallId;
    private int rowNumber;
    private int seatNumber;
    private boolean isVip;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
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
