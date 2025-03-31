package api;

import model.customer;
import model.Reservation;
import model.IRoomInterface;
import service.CustomerService;
import service.ReservationServices;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * HotelResource class provides customer-related functionalities,
 * including retrieving customer details, booking rooms, and managing reservations.
 */
public class HotelResource {

    // Singleton instance of HotelResource
    private static final HotelResource SINGLETON = new HotelResource();

    // Services for handling customer and reservation operations
    private final CustomerService customerService = CustomerService.getSingleton();
    private final ReservationServices reservationService = ReservationServices.getSingleton();

    // Private constructor to enforce Singleton pattern
    private HotelResource() {}


    /**
     * Retrieves the singleton instance of HotelResource.
     *
     * @return the single instance of HotelResource
     */
    public static HotelResource getSingleton() {
        return SINGLETON;
    }

     /**
     * Fetches a customer by email ID.
     *
     * @param emailId the email ID of the customer
     * @return the customer object if found, otherwise null
     */
    public customer getCustomer(String email_id) {
        return customerService.getCustomer(email_id);
    }

     /**
     * Creates a new customer account.
     *
     * @param emailId the email ID of the customer
     * @param firstName the first name of the customer
     * @param lastName the last name of the customer
     */
    public void createACustomer(String email_id, String fName, String lName) {
        customerService.addCustomer(email_id, fName, lName);
    }

     /**
     * Retrieves a room by its room number.
     *
     * @param roomNumber the room number
     * @return the room object if found, otherwise null
     */
    public IRoomInterface getRoom(String room_number) {
        return reservationService.getARoom(room_number);
    }

    /**
     * Books a room for a customer.
     *
     * @param customerEmail the email ID of the customer
     * @param room the room to be booked
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     * @return the reservation object
     */
    public Reservation bookARoom(String customeremail_id, IRoomInterface room, Date Check_in_date, Date check_out_date) {
        return reservationService.reserveARoom(getCustomer(customeremail_id), room, Check_in_date, check_out_date);
    }

    /**
     * Retrieves all reservations made by a customer.
     *
     * @param customerEmail the email ID of the customer
     * @return a collection of reservations, or an empty list if none found
     */
    public Collection<Reservation> getCustomersReservations(String customeremail_id) {
        final customer customer = getCustomer(customeremail_id);

        if (customer == null) {
            return Collections.emptyList();
        }

        return reservationService.getCustomersReservation(getCustomer(customeremail_id));
    }

    /**
     * Finds available rooms for a given date range.
     *
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     * @return a collection of available rooms
     */
    public Collection<IRoomInterface> findARoom(final Date checkIn, final Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }

    /**
     * Finds alternative rooms if no rooms are available for the given date range.
     *
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     * @return a collection of alternative available rooms
     */
    public Collection<IRoomInterface> retrieveAlternateRooms(final Date checkIn, final Date checkOut) {
        return reservationService.retrieveAlternateRooms(checkIn, checkOut);
    }

     /**
     * Adds default days to a given date to provide alternative check-in/check-out dates.
     *
     * @param date the original date
     * @return the adjusted date with additional days
     */
    public Date defaultDayIncrement(final Date date) {
        return reservationService.defaultDayIncrement(date);
    }
}
