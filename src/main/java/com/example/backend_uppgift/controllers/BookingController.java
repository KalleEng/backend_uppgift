package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.DTO.CompressedRoomDTO;
import com.example.backend_uppgift.DTO.DetailedBookingDTO;
import com.example.backend_uppgift.DTO.DetailedRoomDTO;
import com.example.backend_uppgift.Services.BookingService;
import com.example.backend_uppgift.Services.RoomService;
import com.example.backend_uppgift.models.Booking;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @RequestMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id, Model model){
        bookingService.deleteBooking(id);
        return getBookingsFull(model);
    }

    @RequestMapping("/create")
    public void createBooking(@RequestParam LocalDate startDate,
                              @RequestParam LocalDate endDate,
                              @RequestParam Long roomId,
                              @RequestParam Long customerId,
                              @RequestParam int numberOfPeople){
        if(roomService.isAvailable(roomId,startDate,endDate,numberOfPeople)){
            bookingService.createBooking(startDate,endDate,roomId,customerId,numberOfPeople);
        } else{
            System.out.println("False");
        }
    }

    @RequestMapping("/search")
    public String searchDateByRange(@RequestParam LocalDate startDate,
                                    @RequestParam LocalDate endDate,
                                    @RequestParam int numberOfPeople,
                                    Model model){
        List<CompressedRoomDTO> availableRooms = bookingService.findAvailableRooms(startDate, endDate,numberOfPeople);
        List<String> errorList = new ArrayList<>();
        if (availableRooms.isEmpty()){
            errorList.add("No rooms are available");
        }
        model.addAttribute("availableRooms", availableRooms);
        model.addAttribute("searchStart", startDate);
        model.addAttribute("searchEnd", endDate);
        model.addAttribute("numberOfPeople",numberOfPeople);
        model.addAttribute("error", errorList);
        model.addAttribute("roomId","Room ID:");
        model.addAttribute("bedCap","Bed Capacity:");
        return "roomSearch";
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

    @RequestMapping("/edit/{id}")
    public String editBooking(@PathVariable Long id, Model model){
        Booking booking = bookingService.findById(id);
        model.addAttribute("booking",booking);
        return "updateBookingForm";
    }

    @PostMapping("/update")
    public String saveEditedBooking(Model model, Booking booking){
        bookingService.saveBooking(booking);
        List<Booking> bookingList = bookingService.findAll();
        model.addAttribute("allBookings", bookingList);
        return "redirect:/bookings/all";
    }
}
