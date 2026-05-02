package com.ntgschool.easystay.Services;

import com.ntgschool.easystay.Entities.Facility;
import com.ntgschool.easystay.Entities.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<Room> getAllRooms();
    Room getRoomById(Long id);
    Room createRoom(Room room);
    Room updateRoom(Long id, Room room);
    void deleteRoom(Long id);
}
