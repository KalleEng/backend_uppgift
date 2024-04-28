package com.example.backend_uppgift.Services.Impl;

import com.example.backend_uppgift.DTO.CompressedRoomDTO;
import com.example.backend_uppgift.DTO.DetailedRoomDTO;
import com.example.backend_uppgift.Services.RoomService;
import com.example.backend_uppgift.models.Room;
import com.example.backend_uppgift.repositories.BookingRepo;
import com.example.backend_uppgift.repositories.RoomRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepo roomRepo;
    private final BookingRepo bookingRepo;

    public RoomServiceImpl(RoomRepo roomRepo, BookingRepo bookingRepo) {
        this.roomRepo = roomRepo;
        this.bookingRepo = bookingRepo;
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

    @Override
    public boolean isAvailable(Long roomId, LocalDate startDate, LocalDate endDate) {
        return bookingRepo.findByRoomId(roomId).stream()
                .noneMatch(booking ->
                        !(endDate.isBefore(booking.getStartDate()) || startDate.isAfter(booking.getEndDate())));
    }
}
