package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.DTO.DetailedBookingDTO;
import com.example.backend_uppgift.Services.BookingService;
import com.example.backend_uppgift.Services.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;
    private final RoomService roomService;

    public BookingController(BookingService bookingService, RoomService roomService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
    }

    @RequestMapping("/get")
    public List<DetailedBookingDTO> getBooking(){
        return bookingService.getAllBookings();
    }

    @RequestMapping("/delete/{id}")
    public void deleteBooking(@PathVariable Long id){
        bookingService.deleteBooking(id);
    }

    @RequestMapping("/create")
    public void createBooking(@RequestParam LocalDate startDate,
                              @RequestParam LocalDate endDate,
                              @RequestParam Long roomId,
                              @RequestParam Long customerId){
        if(roomService.isAvailable(roomId,startDate,endDate)){
            bookingService.createBooking(startDate,endDate,roomId,customerId);
        } else{
            System.out.println("False");
        }
    }

    @RequestMapping("/all")
    public String getBookingsFull(Model model){
        List<DetailedBookingDTO> allBookings = bookingService.getAllBookings();
        model.addAttribute("allBookings", allBookings);
        model.addAttribute("bookingId","Booking id:");
        model.addAttribute("roomId","Room id:");
        model.addAttribute("from","From:");
        model.addAttribute("until","Until:");
        return "getBookingsFull";
    }
}
