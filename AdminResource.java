package api;

import model.Reservation;
import model.customer;
import model.IRoomInterface;
import service.CustomerService;
import service.ReservationServices;

import java.util.Collection;
import java.util.List;

/**
 * AdminResource class provides administrative functionalities,
 * including managing customers, rooms, and reservations.
 */
public class AdminResource {

     // Singleton instance of AdminResource
    private static final AdminResource SINGLETON = new AdminResource();

    // Services for handling customer and reservation operations
    private final CustomerService customerService = CustomerService.getSingleton();
    private final ReservationServices reservationService = ReservationServices.getSingleton();

    // Private constructor to enforce Singleton pattern
    private AdminResource() {}

    public static AdminResource getSingleton() {
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
     * Adds a list of rooms to the system.
     *
     * @param rooms a list of rooms to be added
     */
    public void addRoom(List<IRoomInterface> rooms) {
        rooms.forEach(reservationService::addRoom);
    }

    /**
     * Retrieves all available rooms.
     *
     * @return a collection of all rooms
     */
    public Collection<IRoomInterface> getAllRooms() {
        return reservationService.getAllRooms();
    }

     /**
     * Retrieves all registered customers.
     *
     * @return a collection of all customers
     */
    public Collection<customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    /**
     * Displays all reservations currently made in the system.
     */
    public void listAllReservations() {
        reservationService.printAllReservation();
    }

    /**
     * Checks if a room with the given room number exists.
     *
     * @param roomNumber the room number to check
     * @return true if the room exists, otherwise false
     */
    public boolean roomExists(String room_number) {
        return ReservationServices.getSingleton().getARoom(room_number) != null;
    }
}
