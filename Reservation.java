package model;

import model.customer;
import model.IRoomInterface;

import java.util.Date;

/**
 * Represents a reservation made by a customer for a specific room.
 * Stores details such as customer information, room details,
 * check-in date, and check-out date.
 */
public class Reservation {

    private final customer customer;
    private final IRoomInterface room;
    private final Date Check_in_date;
    private final Date check_out_date;


    /**
     * Constructs a new reservation.
     * 
     * @param customer    The customer making the reservation.
     * @param room        The room being reserved.
     * @param checkInDate The check-in date of the reservation.
     * @param checkOutDate The check-out date of the reservation.
     */
    public Reservation(final customer customer, final IRoomInterface room,
                       final Date Check_in_date, final Date check_out_date) {
        this.customer = customer;
        this.room = room;
        this.Check_in_date = Check_in_date;
        this.check_out_date = check_out_date;
    }

     /**
     * Retrieves the reserved room.
     * 
     * @return The reserved room.
     */
    public IRoomInterface getRoom() {
        return this.room;
    }

    /**
     * Retrieves the check-in date of the reservation.
     * 
     * @return The check-in date.
     */
    public Date getCheck_in_date() {
        return this.Check_in_date;
    }

     /**
     * Retrieves the check-out date of the reservation.
     * 
     * @return The check-out date.
     */
    public Date getcheck_out_date() {
        return this.check_out_date;
    }

     /**
     * Returns a string representation of the reservation details.
     * 
     * @return A formatted string containing customer, room, check-in, and check-out details.
     */
    @Override
    public String toString() {
        return "Customer: " + this.customer.toString()
                + "\nRoom: " + this.room.toString()
                + "\nCheckIn Date: " + this.Check_in_date
                + "\nCheckOut Date: " + this.check_out_date;
    }
}
