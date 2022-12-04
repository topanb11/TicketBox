package com.example.ensf480.Model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {
    private final UUID id;
    private final String showtimeId;
    private final int seatNo;
    private final String buyerEmail;
    private final Boolean ruFlag;

    public Ticket(@JsonProperty("showtimeId") String showtimeId,
            @JsonProperty("seatNo") int seatNo,
            @JsonProperty("buyerEmail") String buyerEmail,
            @JsonProperty("ruFlag") Boolean ruFlag) {
        this.id = UUID.randomUUID();
        this.showtimeId = showtimeId;
        this.seatNo = seatNo;
        this.buyerEmail = buyerEmail;
        this.ruFlag = ruFlag;
    }

    public UUID getId() {
        return this.id;
    }

    public String getShowtimeId() {
        return this.showtimeId;
    }

    public int getSeatNo() {
        return this.seatNo;
    }

    public String getBuyerEmail() {
        return this.buyerEmail;
    }

    public Boolean getRuFlag() {
        return this.ruFlag;
    }

}
