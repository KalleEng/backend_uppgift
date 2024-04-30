package com.example.backend_uppgift.Views;

import com.example.backend_uppgift.DTO.CompressedBookingDTO;
import com.example.backend_uppgift.DTO.CompressedRoomDTO;
import com.example.backend_uppgift.DTO.DetailedBookingDTO;
import com.example.backend_uppgift.Services.BookingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/bookingsview")
public class BookingViewController {

    private final BookingService bookingService;

    public BookingViewController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RequestMapping("/all")
    public String getBookingsFull(Model model){
        List<DetailedBookingDTO> bookingList = bookingService.getAllBookings();
        model.addAttribute("allBookings", bookingList);
        model.addAttribute("name","Booking name");
        return "getBookingsFull";
    }

    @RequestMapping("/search")
    public String searchDateByRange(@RequestParam LocalDate startDate,
                                    @RequestParam LocalDate endDate,
                                    Model model){
        List<CompressedRoomDTO> availableRooms = bookingService.findAvailableRooms(startDate, endDate);
        model.addAttribute("availableRooms", availableRooms);
        model.addAttribute("searchStart", startDate);
        model.addAttribute("searchEnd", endDate);
        return "roomSearch";
    }
}
