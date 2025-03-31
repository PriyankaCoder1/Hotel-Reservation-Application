package model;

import model.RoomType;

/**
 * Represents a free room in the hotel, which has a price of 0.0.
 */
public class FreeRoom extends Room {

    /**
     * Constructs a FreeRoom object.
     *
     * @param roomNumber The room number.
     * @param roomType The type of the room (SINGLE or DOUBLE).
     */
    public FreeRoom(final String room_number, final RoomType enumeration) {
        super(room_number, 0.0, enumeration);
    }

    /**
     * Returns a string representation of the FreeRoom.
     *
     * @return A formatted string indicating that the room is free.
     */
    @Override
    public String toString() {
        return "FreeRoom => " + super.toString();
    }

}
