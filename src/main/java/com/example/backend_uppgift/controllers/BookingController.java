package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.DTO.CompressedRoomDTO;
import com.example.backend_uppgift.DTO.DetailedBookingDTO;
import com.example.backend_uppgift.Services.BookingService;
import com.example.backend_uppgift.Services.DiscountService;
import com.example.backend_uppgift.Services.RoomService;
import com.example.backend_uppgift.models.Booking;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/bookings")
@PreAuthorize("isAuthenticated()")
public class BookingController {
    private final BookingService bookingService;
    private final DiscountService discountService;
    private final RoomService roomService;

    public BookingController(BookingService bookingService, DiscountService discountService, RoomService roomService) {
        this.bookingService = bookingService;
        this.discountService = discountService;
        this.roomService = roomService;
    }

    @RequestMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id, Model model){
        bookingService.deleteBooking(id);
        return getBookingsFull(model);
    }

    @GetMapping("/search")
    public String searchDateByRange(@RequestParam LocalDate startDate,
                                    @RequestParam LocalDate endDate,
                                    @RequestParam int numberOfPeople,
                                    Model model){
        List<String> errorList = new ArrayList<>();
        if (endDate.isBefore(startDate)){
            errorList.add("End Date can't be before Start Date");
        }
        if (numberOfPeople <= 0){
            errorList.add("Number of people can't be 0 or below");
        } else {
            List<CompressedRoomDTO> availableRooms = bookingService.findAvailableRooms(startDate, endDate, numberOfPeople);
            if (availableRooms.isEmpty()){
                errorList.add("No rooms are available");
            }
            model.addAttribute("availableRooms", availableRooms);
        }
        model.addAttribute("searchStart", startDate);
        model.addAttribute("searchEnd", endDate);
        model.addAttribute("errors", errorList);
        model.addAttribute("numberOfPeople",numberOfPeople);
        model.addAttribute("roomId","Room ID:");
        model.addAttribute("bedCap","Bed Capacity:");
        model.addAttribute("price", "Price: ");
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
        model.addAttribute("total","Total:");
        model.addAttribute("sek"," SEK");
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

        booking.setTotal(discountService.calculateTotal(booking.getStartDate(),
                booking.getEndDate(),
                roomService.getRoomById(booking.getRoom().getId()).getId(), booking.getCustomer().getId()));
        System.out.println(booking.getTotal());
        bookingService.saveBooking(booking);
        List<Booking> bookingList = bookingService.findAll();
        model.addAttribute("allBookings", bookingList);
        return "redirect:/bookings/all";
    }
}
