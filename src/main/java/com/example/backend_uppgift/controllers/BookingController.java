package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.models.Booking;
import com.example.backend_uppgift.repositories.BookingRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingRepo bookingRepo;

    public BookingController(BookingRepo bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    @RequestMapping("/get")
    public List<Booking> getBooking(){
        return bookingRepo.findAll();
    }
}
