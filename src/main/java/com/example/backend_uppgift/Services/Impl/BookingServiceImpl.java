package com.example.backend_uppgift.Services.Impl;

import com.example.backend_uppgift.DTO.CompressedBookingDTO;
import com.example.backend_uppgift.DTO.CompressedCustomerDTO;
import com.example.backend_uppgift.DTO.CompressedRoomDTO;
import com.example.backend_uppgift.DTO.DetailedBookingDTO;
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
                .compRoom(new CompressedRoomDTO(booking.getRoom().getId()))
                .build();
    }

    private CompressedRoomDTO roomToCompRoomDTO(Room room) {
        return CompressedRoomDTO.builder().id(room.getId()).build();
    }

    @Override
    public DetailedBookingDTO bookingToDetailedBookingDTO(Booking booking) {
        return DetailedBookingDTO.builder()
                .id(booking.getId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .compCustomerDTO(new CompressedCustomerDTO(booking.getCustomer().getId(),
                        booking.getCustomer().getName()))
                .compRoom(new CompressedRoomDTO(booking.getRoom().getId()))
                .build();
    }

    @Override
    public List<DetailedBookingDTO> getAllBookings() {
        return bookingRepo.findAll().stream().map(b -> bookingToDetailedBookingDTO(b)).toList();
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepo.deleteById(id);
    }

    @Override
    public void createBooking(@RequestParam LocalDate startDate,
                              @RequestParam LocalDate endDate,
                              @RequestParam Long roomId,
                              @RequestParam Long customerId){
        if (!roomService.isAvailable(roomId,startDate,endDate)){
            throw new RuntimeException("Room not available these dates");
        }
        bookingRepo.save(new Booking(
                startDate,
                endDate,
                roomRepo.findById(roomId).orElse(null),
                customerRepo.findById(customerId).orElse(null)));
    }

    /*public CompressedCustomerDTO customerToCompCustomerDTO(Customer customer){
        return CompressedCustomerDTO.builder().id(customer.getId()).name(customer.getName()).build();
    }*/
}
