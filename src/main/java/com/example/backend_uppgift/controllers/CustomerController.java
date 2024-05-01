package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.DTO.DetailedBookingDTO;
import com.example.backend_uppgift.DTO.DetailedCustomerDTO;
import com.example.backend_uppgift.Services.BookingService;
import com.example.backend_uppgift.Services.CustomerService;
import com.example.backend_uppgift.Services.RoomService;
import com.example.backend_uppgift.models.Customer;
import com.example.backend_uppgift.repositories.CustomerRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final BookingService bookingService;

    private final RoomService roomService;

    public CustomerController(CustomerService customerService, BookingService bookingService, RoomService roomService) {
        this.customerService = customerService;
        this.bookingService = bookingService;
        this.roomService = roomService;
    }

    @RequestMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id, Model model){
        customerService.deleteById(id);
        return getCustomersFull(model);
    }

    @RequestMapping("/all")
    public String getCustomersFull(Model model){
        List<DetailedCustomerDTO> customerList = customerService.getAllCustomers();
        model.addAttribute("allCustomers", customerList);
        model.addAttribute("name","Customer name");
        //model.addAttribute("bookingId",customerList.stream().map(b-> b.getCompBookingId()))
        return "getCustomersFull";
    }

    @RequestMapping("/edit/{id}")
    public String editCustomer(@PathVariable Long id,Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        model.addAttribute("name","customerName");
        return "updateCustomerForm";
    }

    @PostMapping("/update")
    public String saveEditedCustomer(Model model, Customer customer){
        customerService.saveCustomer(customer);
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("allCustomers",customerList);
        model.addAttribute("name","Customer name");
        return "getCustomersFull";
    }

    @RequestMapping("/customerBookings/{id}")
    public String showCustomerBookings(@PathVariable Long id, Model model){
        List<DetailedBookingDTO> allBookings = bookingService.getBookingsByCustomerId(id);
        model.addAttribute("allBookings", allBookings);
        model.addAttribute("bookingsHeader","Bookings by customer: ");
        model.addAttribute("bookingId","Booking id:");
        model.addAttribute("roomId","Room id:");
        model.addAttribute("from","From:");
        model.addAttribute("until","Until:");
        model.addAttribute("customerName", allBookings.stream()
                .map(c -> c.getCompCustomerDTO().getName())
                .findFirst().orElse(null));
        return "showAllBookings";
    }

    @RequestMapping("/add")
    public String addCustomer(){
        return "addNewCustomer";
    }

    @RequestMapping("/added")
    public String addedCustomer(@RequestParam String name,
                                @RequestParam String email){
        customerService.saveCustomer(new Customer(name,email));
        return "redirect:/customers/all";
    }

    @RequestMapping("/createBooking/{id}")
    public String createBooking(@PathVariable Long id, Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        model.addAttribute("name","customerName");
        return "newBooking";
    }

    @PostMapping("/confirmBooking")
    public String confirmBooking(@RequestParam LocalDate startDate,
                                 @RequestParam LocalDate endDate,
                                 @RequestParam Long roomId,
                                 @RequestParam Long customerId,
                                 Model model){
        List<String> errorList = new ArrayList<>();
        if(roomService.isAvailable(roomId,startDate,endDate)){
            bookingService.createBooking(startDate,endDate,roomId,customerId);
        }
        if (roomService.getRoomById(roomId) == null){
            errorList.add("Room doesn't exist");
            model.addAttribute("errors", errorList);
            return "error";
        }if (!roomService.isAvailable(roomId,startDate,endDate)){
            errorList.add("Booking not available");
            model.addAttribute("errors", errorList);
            return "error";
        }
        return "redirect:/customers/all";
    }

}
