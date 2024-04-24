package com.example.backend_uppgift.Services;

import com.example.backend_uppgift.DTO.CompressedRoomDTO;
import com.example.backend_uppgift.DTO.DetailedRoomDTO;
import com.example.backend_uppgift.models.Room;

import java.util.List;

public interface RoomService {
    public CompressedRoomDTO roomToCompRoomDTO(Room room);

    public DetailedRoomDTO roomToDetailedRoomDTO(Room room);
    public List<DetailedRoomDTO> getAllRooms();
    public DetailedRoomDTO getRoomById(Long id);
}
