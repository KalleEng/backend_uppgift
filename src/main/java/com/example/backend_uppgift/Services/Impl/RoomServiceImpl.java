package com.example.backend_uppgift.Services.Impl;

import com.example.backend_uppgift.DTO.CompressedRoomDTO;
import com.example.backend_uppgift.DTO.DetailedRoomDTO;
import com.example.backend_uppgift.Services.RoomService;
import com.example.backend_uppgift.models.Room;
import com.example.backend_uppgift.repositories.RoomRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepo roomRepo;

    public RoomServiceImpl(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    @Override
    public CompressedRoomDTO roomToCompRoomDTO(Room room){
        return CompressedRoomDTO.builder().id(room.getId()).build();
    }

    @Override
    public DetailedRoomDTO roomToDetailedRoomDTO(Room room){
        return DetailedRoomDTO.builder()
                .id(room.getId())
                .isDoubleRoom(room.isDoubleRoom())
                .isAvailable(room.isAvailable())
                .numberOfBeds(room.getNumberOfBeds())
                .build();
    }

    @Override
    public List<DetailedRoomDTO> getAllRooms() {
        return roomRepo.findAll().stream().map(r-> roomToDetailedRoomDTO(r)).toList();
    }

    @Override
    public DetailedRoomDTO getRoomById(Long id) {
        return roomRepo.findById(id).map(r-> roomToDetailedRoomDTO(r)).orElse(null);
    }
}
