import api.AdminResource;
import model.customer;
import model.IRoomInterface;
import model.Room;
import model.RoomType;

import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class AdminMenu {

    private static final AdminResource admin_resource = AdminResource.getSingleton();

    // Main method to display the Admin Menu
    public static void adminMenu() {
        String line = "";
        final Scanner scanner = new Scanner(System.in);

        DisplayFuntion();  // Display the menu options

        try {
            do {
                line = scanner.nextLine();

                if (line.length() == 1) {
                    switch (line.charAt(0)) {
                        case '1':
                            listAllCustomers();// Show all registered customers
                            break;
                        case '2':
                            listAllRooms();// Show all available rooms
                            break;
                        case '3':
                            listAllReservations();// Show all reservations
                            break;
                        case '4':
                            addRoom(); // Add a new room to the system
                            break;
                        case '5':
                            System.out.println("Exiting to Main Menu...");
                            MainMenu.DisplayMainFuntion();  // Go back to the main menu
                            break;
                        default:
                            System.out.println("Unknown action\n");
                            break;
                    }

                } else {
                    System.out.println("Error: Invalid action\n");
                    DisplayFuntion(); // Re-display menu in case of invalid input
                }
            } while (line.charAt(0) != '5' || line.length() != 1);
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Empty input received. Exiting program...");
        }
    }

    // Method to display the Admin Menu options
    private static void DisplayFuntion() {
        System.out.print("\nAdmin Menu\n" +
                "--------------------------------------------\n" +
                "1. See all Customers\n" +
                "2. See all Rooms\n" +
                "3. See all Reservations\n" +
                "4. Add a Room\n" +
                "5. Back to Main Menu\n" +
                "--------------------------------------------\n" +
                "Please select a number for the menu option:\n");
    }

    private static void addRoom() {
        final Scanner scanner = new Scanner(System.in);

        String room_number;

        // Loop until a valid room number is entered
        while (true) {
            System.out.println("Enter room number (numeric and greater than 0):");
            room_number = scanner.nextLine();

            // Check if the room number is numeric and greater than 0
            if (isNumeric(room_number) && Integer.parseInt(room_number) > 0) {
                // Check if the room number already exists
                if (admin_resource.roomExists(room_number)) {
                    System.out.println("Room number " + room_number + " already exists. Please enter a different room number.");
                } else {
                    break; // Exit the loop if valid and unique
                }
            }
            else {
                System.out.println("Invalid room number! Please enter a valid numeric room number greater than 0.");
            }
        }

        System.out.println("Enter Room_price per night:");
        final double roomRoom_price = enterRoom_price(scanner);

        System.out.println("Enter room type: 1 for single bed, 2 for double bed:");
        final RoomType roomType = selectRoomType(scanner);

        final Room room = new Room(room_number, roomRoom_price, roomType);

        admin_resource.addRoom(Collections.singletonList(room));
        System.out.println("Room added successfully!");

        System.out.println("Would like to add another room? y/n");
        addMoreRooms();
    }

    private static double enterRoom_price(final Scanner scanner) {

        while (true) {
            try {
                double Room_price = Double.parseDouble(scanner.nextLine());
                if (Room_price >=0) {
                    return Room_price; // Valid Room_price entered
                } else {
                    System.out.println("Error:  Please enter a valid Room_price.");
                }
            } catch (NumberFormatException exp) {
                System.out.println("Invalid room Room_price! Please, enter a valid double number. " +
                        "Decimals should be separated by point (.)");
            }
        }
    }

    // Helper method to check if a string is numeric
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str); // Try to parse the string as an integer
            return true; // If successful, it's numeric
        } catch (NumberFormatException e) {
            return false; // If it fails, it's not numeric
        }
    }

    // Method to validate and enter room type
    private static RoomType selectRoomType(final Scanner scanner) {
        try {
            return RoomType.valueOfLabel(scanner.nextLine());
        } catch (IllegalArgumentException exp) {
            System.out.println("Invalid room type! Please, choose 1 for single bed or 2 for double bed:");
            return selectRoomType(scanner);
        }
    }

    // Method to prompt user if they want to add another room
    private static void addMoreRooms() {
        final Scanner scanner = new Scanner(System.in);

        try {
            String anotherRoom;

            anotherRoom = scanner.nextLine();

            while ((anotherRoom.charAt(0) != 'y' && anotherRoom.charAt(0) != 'n')
                    || anotherRoom.length() != 1) {
                System.out.println("Please enter y (Yes) or n (No)");
                anotherRoom = scanner.nextLine();
            }

            if (anotherRoom.charAt(0) == 'y') {
                addRoom();
            } else if (anotherRoom.charAt(0) == 'n') {
                DisplayFuntion();
            } else {
                addMoreRooms();
            }
        } catch (StringIndexOutOfBoundsException ex) {
            addMoreRooms();
        }
    }

     // Method to display all rooms
    private static void listAllRooms() {
        Collection<IRoomInterface> rooms = admin_resource.getAllRooms();

        if(rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            admin_resource.getAllRooms().forEach(System.out::println);
            DisplayFuntion();
        }
    }

    // Method to display all customers
    private static void listAllCustomers() {
        Collection<customer> customers = admin_resource.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            admin_resource.getAllCustomers().forEach(System.out::println);
        }
        DisplayFuntion();
    }
     // Method to display all reservations
    private static void listAllReservations() {
        admin_resource.listAllReservations();
    }
}
