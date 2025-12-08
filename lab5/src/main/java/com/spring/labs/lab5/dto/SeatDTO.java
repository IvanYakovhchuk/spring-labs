package com.spring.labs.lab5.dto;

import com.spring.labs.lab5.entity.Seat;
import io.swagger.v3.oas.annotations.media.Schema;

public class SeatDTO {
    @Schema(description = "Seat id")
    private long id;

    @Schema(description = "Cinema hall id for the seat")
    private int cinemaHall;

    @Schema(description = "Row number of the seat")
    private int rowNumber;

    @Schema(description = "Seat number in the row")
    private int seatNumber;

    @Schema(description = "VIP status of the seat")
    private boolean vip;

    public SeatDTO(long id, int cinemaHall, int rowNumber, int seatNumber, boolean vip) {
        this.id = id;
        this.cinemaHall = cinemaHall;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.vip = vip;
    }

    public static SeatDTO fromEntity(Seat seat) {
        return new SeatDTO(
                seat.getId(),
                seat.getCinemaHall(),
                seat.getRowNumber(),
                seat.getSeatNumber(),
                seat.getIsVip()
        );
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

    public void setCinemaHallId(int cinemaHall) {
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
        return vip;
    }

    public void setIsVip(boolean vip) {
        this.vip = vip;
    }
}
