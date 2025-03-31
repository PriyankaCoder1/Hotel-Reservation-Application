package model;

/**
 * Enum representing different types of rooms available in the hotel.
 */
public enum RoomType {
    SINGLE("1"),
    DOUBLE("2");

    public final String label;

    /**
     * Constructor for RoomType enum.
     *
     * @param label The string representation of the room type.
     */
    private RoomType(String label) {
        this.label = label;
    }

    /**
     * Retrieves the label associated with the room type.
     *
     * @return The label as a string.
     */
    public String getLabel(){
        return label;
    }

    **
     * Finds the RoomType corresponding to a given label.
     *
     * @param label The label to look up.
     * @return The corresponding RoomType.
     * @throws IllegalArgumentException If the label does not match any RoomType.
     */
    public static RoomType valueOfLabel(String label) {
        for (RoomType roomType : values()) {
            if (roomType.label.equals(label)) {
                return roomType;
            }
        }
        throw new IllegalArgumentException();
    }
}
