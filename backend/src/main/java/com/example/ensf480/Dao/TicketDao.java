package com.example.ensf480.Dao;

import java.util.List;
import java.util.UUID;

import com.example.ensf480.Model.Ticket;

// Interface for Ticket functions
public interface TicketDao {
		/**
		 * Function to add a Ticket entity to database table
		 * @param ticket - Ticket object
		 * @return Ticket
		 */
    Ticket createTicket(Ticket ticket);

		/**
		 * Function to remove a Ticket from database table
		 * @param id - Ticket id
		 * @param isRu - Flag if user that purchased ticket is registered
		 * @return Stirng
		 */
    String deleteTicket(String id, Boolean isRu);

		/**
		 * Function to cancel a pending ticket
		 * @param id - Ticket id
		 */
    void cancelPendingTicket(String id);

		/**
		 * Function to get all seats based on showtime
		 * @param showtime_id - Unique id of specific showtime
		 * @return List<Integer>
		 */
    List<Integer> getSeatsByShowtime(UUID showtime_id);

}
