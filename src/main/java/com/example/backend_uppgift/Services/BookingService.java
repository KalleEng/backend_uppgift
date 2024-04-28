package com.example.backend_uppgift.Services;

import com.example.backend_uppgift.DTO.CompressedBookingDTO;
import com.example.backend_uppgift.DTO.DetailedBookingDTO;
import com.example.backend_uppgift.models.Booking;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    public CompressedBookingDTO bookingToCompBookingDTO(Booking booking);
    public DetailedBookingDTO bookingToDetailedBookingDTO(Booking booking);

    List<DetailedBookingDTO> getAllBookings();
    void deleteBooking(Long id);

    List<DetailedBookingDTO> getBookingsByCustomerId(Long id);

    void createBooking(@RequestParam LocalDate startDate,
                       @RequestParam LocalDate endDate,
                       @RequestParam Long roomId,
                       @RequestParam Long customerId);

    void checkAvailability(LocalDate startDate, LocalDate endDate, Long roomId);
}
