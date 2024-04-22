package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.models.Booking;
import com.example.backend_uppgift.repositories.BookingRepo;
import com.example.backend_uppgift.repositories.CustomerRepo;
import com.example.backend_uppgift.repositories.RoomRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String addBooking(@RequestParam LocalDate startDate,
                             @RequestParam LocalDate endDate,
                             @RequestParam Long roomId,
                             @RequestParam Long customerId){
        bookingRepo.save(new Booking(startDate, endDate,
                roomRepo.findById(roomId).orElse(null),
                customerRepo.findById(customerId).orElse(null)));
        return "Booking added";
    }
}
