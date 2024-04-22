package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.models.Booking;
import com.example.backend_uppgift.models.Customer;
import com.example.backend_uppgift.models.Room;
import com.example.backend_uppgift.repositories.BookingRepo;
import com.example.backend_uppgift.repositories.CustomerRepo;
import com.example.backend_uppgift.repositories.RoomRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private final CustomerRepo customerRepo;
    private final BookingRepo bookingRepo;
    private final RoomRepo roomRepo;

    public BookingController(CustomerRepo customerRepo, BookingRepo bookingRepo, RoomRepo roomRepo) {
        this.customerRepo = customerRepo;
        this.bookingRepo = bookingRepo;
        this.roomRepo = roomRepo;
    }

    @RequestMapping("/get")
    public List<Booking> getBooking(){
        return bookingRepo.findAll();
    }

    @PostMapping("/addbooking")
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
    }
}
