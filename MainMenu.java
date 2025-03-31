import api.HotelResource;
import model.Reservation;
import model.IRoomInterface;
import model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

/**
 * Main menu class for the hotel reservation system.
 * Handles user interactions for booking and managing reservations.
 */


public class MainMenu {

        private static final String DEFAULT_DISPLAY_DATE = "MM/dd/yyyy";
        private static final HotelResource hotelResource = HotelResource.getSingleton();

    public static void main(String[] args) {
        DisplayMainFuntion(); // Display the main menu
        handleUserInput(); // Start the main menu logic
    }

     /**
     * Displays the main menu options.
     */
     public static void DisplayMainFuntion ()
        {
            System.out.print("\nWelcome to the Hotel Reservation Application\n" +
                    "--------------------------------------------\n" +
                    "1. Find and reserve a room\n" +
                    "2. See my reservations\n" +
                    "3. Create an Account\n" +
                    "4. Admin\n" +
                    "5. Exit\n" +
                    "--------------------------------------------\n" +
                    "Please select a number for the menu option:\n");
        }

    /**
     * Handles user input and navigates through menu options.
     */

        public static void handleUserInput () {
            String line = "";
            Scanner scanner = new Scanner(System.in);

           //DisplayMainFuntion();

            try {
                do {

                    line = scanner.nextLine();

                    if (line.length() == 1) {
                        switch (line.charAt(0)) {
                            case '1':
                                findandReserveRoom();
                                break;
                            case '2':
                                viewReservations();
                                break;
                            case '3':
                                createUserAccount();
                                break;
                            case '4':
                                AdminMenu.adminMenu();
                                break;
                            case '5':
                                System.out.println("Exiting application. Thank-you Visit Again");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again\n");
                                break;
                        }
                    } else {
                        System.out.println("Error: Invalid input. Please enter a number between 1 and 5.\n");
                        DisplayMainFuntion();

                    }
                } while (line.charAt(0) != '5' || line.length() != 1);
            } catch (StringIndexOutOfBoundsException ex) {
                System.out.println("Empty input received. Exiting program...");
            }
        }

    /**
     * Allows the user to search and book a room.
     */
        private static void findandReserveRoom () {
            final Scanner scanner = new Scanner(System.in);

            System.out.println("Enter Check-In Date mm/dd/yyyy example 02/01/2020");
            Date checkIn = fetchDateInput(scanner);

            System.out.println("Enter Check-Out Date mm/dd/yyyy example 02/21/2020");
            Date checkOut = fetchDateInput(scanner);

            if (checkIn != null && checkOut != null) {
                Collection<IRoomInterface> availableRooms = hotelResource.findARoom(checkIn, checkOut);

                if (availableRooms.isEmpty()) {
                    Collection<IRoomInterface> alternativeRooms = hotelResource.retrieveAlternateRooms(checkIn, checkOut);

                    if (alternativeRooms.isEmpty()) {
                        System.out.println("No rooms found.");
                    } else {
                        final Date alternativeCheckIn = hotelResource.defaultDayIncrement(checkIn);
                        final Date alternativeCheckOut = hotelResource.defaultDayIncrement(checkOut);
                        System.out.println("We've only found rooms on alternative dates:" +
                                "\nCheck-In Date:" + alternativeCheckIn +
                                "\nCheck-Out Date:" + alternativeCheckOut);

                        printRoomsList(alternativeRooms);
                        reserveARoom(scanner, alternativeCheckIn, alternativeCheckOut, alternativeRooms);
                    }
                } else {
                    printRoomsList(availableRooms);
                    reserveARoom(scanner, checkIn, checkOut, availableRooms);
                }
            }
        }

     /**
     * Prompts the user for a date input and validates it.
     */
    private static Date fetchDateInput(final Scanner scanner) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DISPLAY_DATE);
        dateFormat.setLenient(false); // Set lenient to false to strictly parse dates
        while (true) {
            try {
                String input = scanner.nextLine();
                return dateFormat.parse(input); // Parse the input string to a Date object
            } catch (ParseException e) {
                System.out.println("Wrong Date Pattern. Please enter the date in MM-dd-yyyy format."); // Update the message
            }
        }
    }

     /**
     * Handles room reservation process.
     */
    private static void reserveARoom ( final Scanner scanner, final Date Check_in_date,
        final Date check_out_date, final Collection<IRoomInterface> rooms){
            System.out.println("Would you like to book? y/n");
            final String bookRoom = scanner.nextLine();

            if ("y".equals(bookRoom)) {
                System.out.println("Do you have an account with us? y/n");
                final String haveAccount = scanner.nextLine();

                if ("y".equals(haveAccount)) {
                    System.out.println("Enter email_id format: name@domain.com");
                    final String customeremail_id = scanner.nextLine();

                    if (hotelResource.getCustomer(customeremail_id) == null) {
                        System.out.println("Customer not found.\nYou may need to create a new account.");
                    } else {
                        System.out.println("What room number would you like to reserve?");
                        final String room_number = scanner.nextLine();

                        if (rooms.stream().anyMatch(room -> room.getroom_number().equals(room_number))) {
                            final IRoomInterface room = hotelResource.getRoom(room_number);

                            final Reservation reservation = hotelResource
                                    .bookARoom(customeremail_id, room, Check_in_date, check_out_date);
                            System.out.println("Reservation created successfully!");
                            System.out.println(reservation);
                        } else {
                            System.out.println("Error: room number not available.\nStart reservation again.");
                        }
                    }

                    DisplayMainFuntion();
                } else {
                    System.out.println("Please, create an account.");
                    DisplayMainFuntion();
                }
            } else if ("n".equals(bookRoom)) {
                System.out.println("Booking canceled.");
               DisplayMainFuntion();
            } else {
                reserveARoom(scanner, Check_in_date, check_out_date, rooms);
            }
        }

        private static void printRoomsList ( final Collection<IRoomInterface> rooms){
            if (rooms.isEmpty()) {
                System.out.println("No rooms found.");
            } else {
                rooms.forEach(System.out::println);
            }
        }
    /**
     * Displays the reservations for a given customer.
     */
        private static void viewReservations () {
            final Scanner scanner = new Scanner(System.in);

            System.out.println("Enter your email_id format: name@domain.com");
            final String customeremail_id = scanner.nextLine();

            showReservationsList(hotelResource.getCustomersReservations(customeremail_id));
        }

        private static void showReservationsList ( final Collection<Reservation> reservations){
            if (reservations == null || reservations.isEmpty()) {
                System.out.println("No reservations found.");
            } else {
                reservations.forEach(reservation -> System.out.println("\n" + reservation));
            }
            DisplayMainFuntion();
        }
     /**
     * Handles account creation process.
     */
        private static void createUserAccount () {
            final Scanner scanner = new Scanner(System.in);

            String email_id;
            boolean isValidemail_id;

            do {
                System.out.println("Enter email_id format: name@domain.com");
                email_id = scanner.nextLine().toLowerCase().trim();

                // email_id validation regex
                isValidemail_id = email_id.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com$");
                if (!isValidemail_id) {
                    System.out.println("Invalid email_id format. Please enter a valid email_id.");
                }
            } while (!isValidemail_id);

            System.out.println("First Name:");
            final String fName = scanner.nextLine();

            System.out.println("Last Name:");
            final String lName = scanner.nextLine();

            try {
                hotelResource.createACustomer(email_id, fName, lName);
                System.out.println("Account created successfully!");

                DisplayMainFuntion();
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getLocalizedMessage());
                createUserAccount();
            }
        }

        
    }


