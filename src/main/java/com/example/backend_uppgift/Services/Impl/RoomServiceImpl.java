package com.example.backend_uppgift.Services.Impl;

import com.example.backend_uppgift.DTO.CompressedBookingDTO;
import com.example.backend_uppgift.DTO.CompressedRoomDTO;
import com.example.backend_uppgift.DTO.DetailedRoomDTO;
import com.example.backend_uppgift.Services.RoomService;
import com.example.backend_uppgift.models.Booking;
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
                .isAvailable(room.isAvailable())
                .bedCapacity(room.getBedCapacity())
                .bookingList(room.getBookingList().stream().map(booking -> bookingToCompBookingDTO(booking)).toList())
                .build();
    }

    private CompressedBookingDTO bookingToCompBookingDTO(Booking booking) {
        return CompressedBookingDTO.builder()
                .id(booking.getId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
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
    public boolean isAvailable(Long roomId, LocalDate startDate, LocalDate endDate, int numberOfPeople) {
        return bookingRepo.findByRoomId(roomId).stream()
                .noneMatch(booking ->
                        !((endDate.isBefore(booking.getStartDate()) || startDate.isAfter(booking.getEndDate())) && booking.getRoom().getBedCapacity()>=numberOfPeople));
    }

    @Override
    public void deleteById(Long id) {
        roomRepo.deleteById(id);
    }

    @Override
    public void saveRoom(Room room) {
        roomRepo.save(room);
    }

    @Override
    public Room findById(Long id){return roomRepo.findById(id).orElse(null);}
}
