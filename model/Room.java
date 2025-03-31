package model;

import model.RoomType;

import java.util.Objects;

/**
 * Represents a room in the hotel with details such as room number, price, and type.
 */
public class Room implements IRoomInterface {

    private final String room_number;
    private final Double Room_price;
    private final RoomType enumeration;

    /**
     * Constructs a new room.
     * 
     * @param roomNumber The unique room number.
     * @param roomPrice  The price of the room per night.
     * @param roomType   The type of the room (e.g., single, double).
     */
    public Room(final String room_number, final Double Room_price, final RoomType enumeration) {
        this.room_number = room_number;
        this.Room_price = Room_price;
        this.enumeration = enumeration;
    }

    /**
     * Retrieves the room number.
     * 
     * @return The room number.
     */
    public String getroom_number() {
        return this.room_number;
    }

     /**
     * Retrieves the price of the room.
     * 
     * @return The room price.
     */
    public Double getRoomRoom_price() {
        return this.Room_price;
    }

    /**
     * Retrieves the type of the room.
     * 
     * @return The room type.
     */
    public RoomType getRoomType() {
        return this.enumeration;
    }

    /**
     * Checks if the room is free.
     * 
     * @return True if the room price is zero, false otherwise.
     */
    public boolean isFree() {
        return this.Room_price != null && this.Room_price.equals(0.0);
    }

     /**
     * Returns a string representation of the room details.
     * 
     * @return A formatted string containing room details.
     */
    @Override
    public String toString() {
        return "Room Number: " + this.room_number
                + " Room_price: $" + this.Room_price
                + " Enumeration: " + this.enumeration;
    }

    /**
     * Checks if two rooms are equal based on room number.
     * 
     * @param obj The object to compare.
     * @return True if the rooms are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }

        if(!(obj instanceof Room)) {
            return false;
        }

        final Room room = (Room) obj;
        return Objects.equals(this.room_number, room.room_number);
    }

    /**
     * Generates a hash code for the room based on room number.
     * 
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(room_number);
    }
}
