# Hotel-Reservation-Application

## Overview
The **Hotel Reservation Application** is a Java-based system that allows users to book hotel rooms, manage reservations, and create user accounts. It also includes an admin panel to manage customers, rooms, and reservations.

## Features
### User Functionality
- **Find and Reserve a Room**: Users can search for available rooms based on check-in and check-out dates.
- **View Reservations**: Users can view their past and upcoming hotel reservations.
- **Create an Account**: Users need to create an account using an email ID before booking rooms.

### Admin Functionality
- **View All Customers**: Displays all registered customers.
- **View All Rooms**: Lists all available rooms.
- **View All Reservations**: Shows all reservations made.
- **Add a Room**: Admins can add new rooms to the system.

## Technologies Used
- Java (Core Java, Collections, OOP)
- Scanner (for user input)
- Singleton Pattern (for resource management)

## How to Run the Application
1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/hotel-reservation.git
   ```
2. Navigate to the project folder:
   ```sh
   cd hotel-reservation
   ```
3. Compile the Java files:
   ```sh
   javac MainMenu.java
   ```
4. Run the application:
   ```sh
   java MainMenu
   ```

## File Structure
```
hotel-reservation/
│-- src/
│   │-- MainMenu.java  # Handles user interactions
│   │-- AdminMenu.java # Manages admin functionalities
│   │-- api/           # API resources for customer and room management
│   │-- model/         # Models for Room, Reservation, Customer
│-- README.md         # Project documentation
```

## Future Enhancements
- Implement a database for storing room and customer data.
- Add a graphical user interface (GUI) for better user experience.
- Support online payments for reservations.

## Contributors
- **Priyanka Rawat** - Developer




