package model;

import model.RoomType;

/**
 * Interface representing a room in the hotel.
 * Defines methods to retrieve room details such as room number, price, type, and availability.
 */
public interface IRoomInterface {
     public String getroom_number();
     public Double getRoomRoom_price();
     public RoomType getRoomType();
     public boolean isFree();
}
