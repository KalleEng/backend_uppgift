package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.DTO.DetailedBookingDTO;
import com.example.backend_uppgift.Services.BookingService;
import com.example.backend_uppgift.models.Booking;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RequestMapping("/get")
    public List<DetailedBookingDTO> getBooking(){
        return bookingService.getAllBookings();
    }

/*    @PostMapping("/addbooking")
    public ResponseEntity<String> addBooking(@RequestParam LocalDate startDate,
                             @RequestParam LocalDate endDate,
                             @RequestParam Long roomId,
                             @RequestParam Long customerId){

        Room room = roomRepo.findById(roomId).orElse(null);
        if (room == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");

        Customer customer = customerRepo.findById(customerId).orElse(null);
        if (customer == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");

        Booking booking = new Booking(startDate,endDate, room,customer);
        //booking.setCustomerId(customerId);

        bookingRepo.save(booking);

        return ResponseEntity.status(HttpStatus.CREATED).body("Booking added for customer ID: " + customerId);
    }*/
}
