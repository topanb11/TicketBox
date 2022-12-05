package com.example.ensf480.Model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

// Model for Ticket entity
public class Ticket {
    private final UUID id;
    private final String showtimeId;
    private final int seatNo;
    private final String buyerEmail;
    private final Boolean ruFlag;

		/**
		 * Constructor for Ticket object
		 * @param showtimeId - Unique id for showtime
		 * @param seatNo - Seat number
		 * @param buyerEmail - Email of buyer
		 * @param ruFlag - Flag to know if ticket was purchased by a registered user
		 */
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

		/**
		 * Getter for id
		 * @return id: UUID
		 */
    public UUID getId() {
        return this.id;
    }

		/**
		 * Getter for showtimeId
		 * @return showtimeId: String
		 */
    public String getShowtimeId() {
        return this.showtimeId;
    }

		/**
		 * Getter for seat number
		 * @return seatNo: int
		 */
    public int getSeatNo() {
        return this.seatNo;
    }

		/**
		 * Getter for buyer email
		 * @return buyerEmail: String
		 */
    public String getBuyerEmail() {
        return this.buyerEmail;
    }

		/**
		 * Getter for RuFlag
		 * @return ruFlag: Boolean
		 */
    public Boolean getRuFlag() {
        return this.ruFlag;
    }

}
