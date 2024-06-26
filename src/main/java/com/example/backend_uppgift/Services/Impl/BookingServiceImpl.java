package com.example.backend_uppgift.Services.Impl;

import com.example.backend_uppgift.DTO.*;
import com.example.backend_uppgift.Services.BookingService;
import com.example.backend_uppgift.Services.RoomService;
import com.example.backend_uppgift.models.Booking;
import com.example.backend_uppgift.models.Room;
import com.example.backend_uppgift.repositories.BookingRepo;
import com.example.backend_uppgift.repositories.CustomerRepo;
import com.example.backend_uppgift.repositories.RoomRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepo bookingRepo;
    private final RoomRepo roomRepo;
    private final CustomerRepo customerRepo;
    private final RoomService roomService;

    public BookingServiceImpl(BookingRepo bookingRepo, RoomRepo roomRepo, CustomerRepo customerRepo, RoomService roomService) {
        this.bookingRepo = bookingRepo;
        this.roomRepo = roomRepo;
        this.customerRepo = customerRepo;
        this.roomService = roomService;
    }

    @Override
    public CompressedBookingDTO bookingToCompBookingDTO(Booking booking) {
        return CompressedBookingDTO.builder()
                .id(booking.getId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .total(booking.getTotal())
                .compRoom(new CompressedRoomDTO(booking.getRoom().getId(),booking.getRoom().getBedCapacity(),booking.getRoom().getPrice()))
                .build();
    }

    private CompressedRoomDTO roomToCompRoomDTO(Room room) {
        return CompressedRoomDTO.builder().id(room.getId()).bedCapacity(room.getBedCapacity()).build();
    }

    @Override
    public DetailedBookingDTO bookingToDetailedBookingDTO(Booking booking) {
        return DetailedBookingDTO.builder()
                .id(booking.getId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .total(booking.getTotal())
                .compCustomerDTO(new CompressedCustomerDTO(booking.getCustomer().getId(),
                        booking.getCustomer().getName()))
                .compRoom(new CompressedRoomDTO(booking.getRoom().getId(),booking.getRoom().getBedCapacity(),booking.getRoom().getPrice()))
                .build();
    }

    @Override
    public List<DetailedBookingDTO> getAllBookings() {
        return bookingRepo.findAll().stream().map(b -> bookingToDetailedBookingDTO(b)).toList();
    }

    @Override
    public List<DetailedBookingDTO> getBookingsByCustomerId(Long id){
        return bookingRepo.findAll()
                .stream()
                .filter(b -> b.getCustomer().getId() == id)
                .map(this::bookingToDetailedBookingDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepo.deleteById(id);
    }

    @Override
    public void createBooking(@RequestParam LocalDate startDate,
                              @RequestParam LocalDate endDate,
                              @RequestParam Long roomId,
                              @RequestParam Long customerId,
                              @RequestParam int numberOfPeople,
                              @RequestParam double total){
        if (!roomService.isAvailable(roomId,startDate,endDate,numberOfPeople)){
            throw new RuntimeException("Room not available these dates");
        }
        bookingRepo.save(new Booking(
                startDate,
                endDate,
                roomRepo.findById(roomId).orElse(null),
                customerRepo.findById(customerId).orElse(null),
                total)
        );
    }

    @Override
    public Booking findById(Long id) {
        return bookingRepo.findById(id).orElse(null);
    }

    @Override
    public List<Booking> findAll(){return bookingRepo.findAll();}
    @Override
    public void saveBooking(Booking booking){bookingRepo.save(booking);}


    @Override
    public List<CompressedRoomDTO> findAvailableRooms(LocalDate startDate, LocalDate endDate, int numberOfPeople) {
        List<Room> allRooms = roomRepo.findAll();
        List<Booking> bookings = bookingRepo.findByDateRange(startDate, endDate,numberOfPeople);
        Set<Long> bookRoomId = bookings.stream()
                .map(booking -> booking.getRoom().getId())
                .collect(Collectors.toSet());

        return allRooms.stream()
                .filter(room -> !bookRoomId.contains(room.getId()) && room.getBedCapacity()>= numberOfPeople)
                .map(this::roomToCompRoomDTO)
                .collect(Collectors.toList());
    }

}
