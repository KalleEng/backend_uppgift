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

    @PostMapping("/create")
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
        List<DetailedBookingDTO> bookingList = bookingService.getAllBookings();
        model.addAttribute("allBookings", bookingList);
        model.addAttribute("name","Booking name");
        return "getBookingsFull";
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
