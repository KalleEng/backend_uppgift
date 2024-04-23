package com.example.backend_uppgift.Services;

import com.example.backend_uppgift.DTO.CompressedBookingDTO;
import com.example.backend_uppgift.DTO.DetailedBookingDTO;
import com.example.backend_uppgift.models.Booking;

import java.util.List;

public interface BookingService {

    public CompressedBookingDTO bookingToCompBookingDTO(Booking booking);
    public DetailedBookingDTO bookingToDetailedBookingDTO(Booking booking);

    List<DetailedBookingDTO> getAllBookings();
}
