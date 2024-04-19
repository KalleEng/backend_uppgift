package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.models.Booking;
import com.example.backend_uppgift.models.Customer;
import com.example.backend_uppgift.models.Room;
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
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerRepo customerRepo;
    private final BookingRepo bookingRepo;
    private final RoomRepo roomRepo;

    public CustomerController(CustomerRepo customerRepo, BookingRepo bookingRepo, RoomRepo roomRepo) {
        this.customerRepo = customerRepo;
        this.bookingRepo = bookingRepo;
        this.roomRepo = roomRepo;
    }

    @PostMapping("/add")
    public String addCustomer(@RequestParam String name, @RequestParam String email){
        customerRepo.save(new Customer(name, email));
        return "Saved customer ";
    }

    @RequestMapping("/get")
    public List<Customer> getCustomer(){
        return customerRepo.findAll();
    }

    @PostMapping("/addbooking")
    public String addBooking(@RequestParam LocalDate startDate,
                             @RequestParam LocalDate endDate,
                             @RequestParam Long id){
        bookingRepo.save(new Booking(startDate, endDate, roomRepo.findById(id).orElse(null)));
        return "Booking added";
    }
}
