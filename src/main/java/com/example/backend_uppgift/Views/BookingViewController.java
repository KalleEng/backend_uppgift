package com.example.backend_uppgift.Views;

import com.example.backend_uppgift.DTO.DetailedBookingDTO;
import com.example.backend_uppgift.Services.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
