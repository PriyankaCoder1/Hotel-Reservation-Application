package service;

import model.customer;
import model.Reservation;
import model.IRoomInterface;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public class ReservationServices {

    private static final ReservationServices SINGLETON = new ReservationServices();
    private static final int SUGGESTED_ROOMS_EXTENSION_DAYS = 7;

    private final Map<String, IRoomInterface> rooms = new HashMap<>();
    private final Map<String, Collection<Reservation>> reservations = new HashMap<>();

    private ReservationServices() {}

    public static ReservationServices getSingleton() {
        return SINGLETON;
    }

    // Adds a new room to the system
    public void addRoom(final IRoomInterface room) {
        rooms.put(room.getroom_number(), room);
    }

    // Retrieves a room by its room number
    public IRoomInterface getARoom(final String room_number) {
        return rooms.get(room_number);
    }

    // Retrieves all available rooms
    public Collection<IRoomInterface> getAllRooms() {
        return rooms.values();
    }

    // Reserves a room for a customer
    public Reservation reserveARoom(final customer customer, final IRoomInterface room,
                                    final Date Check_in_date, final Date check_out_date) {
        final Reservation reservation = new Reservation(customer, room, Check_in_date, check_out_date);

        Collection<Reservation> customerReservations = getCustomersReservation(customer);

        if (customerReservations == null) {
            customerReservations = new LinkedList<>();
        }

        customerReservations.add(reservation);
        reservations.put(customer.getemail_id(), customerReservations);

        return reservation;
    }

    // Finds available rooms for a given date range
    public Collection<IRoomInterface> findRooms(final Date Check_in_date, final Date check_out_date) {
        return findAvailableRooms(Check_in_date, check_out_date);
    }

    // Suggests alternative rooms by shifting dates by default days
    public Collection<IRoomInterface> retrieveAlternateRooms(final Date Check_in_date, final Date check_out_date) {
        return findAvailableRooms(defaultDayIncrement(Check_in_date), defaultDayIncrement(check_out_date));
    }

    // Finds available rooms that are not currently reserved
    private Collection<IRoomInterface> findAvailableRooms(final Date Check_in_date, final Date check_out_date) {
        final Collection<Reservation> allReservations = getAllReservations();
        final Collection<IRoomInterface> notAvailableRooms = new LinkedList<>();

        for (Reservation reservation : allReservations) {
            if (reservationOverlaps(reservation, Check_in_date, check_out_date)) {
                notAvailableRooms.add(reservation.getRoom());
            }
        }

        return rooms.values().stream().filter(room -> notAvailableRooms.stream()
                        .noneMatch(notAvailableRoom -> notAvailableRoom.equals(room)))
                .collect(Collectors.toList());
    }

    // Extends a given date by the default number of extension days
    public Date defaultDayIncrement(final Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, SUGGESTED_ROOMS_EXTENSION_DAYS);

        return calendar.getTime();
    }

    // Checks if a reservation overlaps with the given dates
    private boolean reservationOverlaps(final Reservation reservation, final Date Check_in_date,
                                        final Date check_out_date){
        return Check_in_date.before(reservation.getcheck_out_date())
                && check_out_date.after(reservation.getCheck_in_date());
    }

    // Retrieves all reservations of a given customer
    public Collection<Reservation> getCustomersReservation(final customer customer) {
        return reservations.get(customer.getemail_id());
    }

     // Prints all reservations
    public void printAllReservation() {
        final Collection<Reservation> reservations = getAllReservations();

        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation + "\n");
            }
        }
    }

    // Retrieves all reservations from the system
    private Collection<Reservation> getAllReservations() {
        final Collection<Reservation> allReservations = new LinkedList<>();

        for(Collection<Reservation> reservations : reservations.values()) {
            allReservations.addAll(reservations);
        }

        return allReservations;
    }
}
