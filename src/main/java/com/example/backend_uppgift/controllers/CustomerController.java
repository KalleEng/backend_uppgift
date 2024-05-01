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
import java.util.stream.Collectors;

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
        List<String> errorList = new ArrayList<>();
        if(!customerService.findById(id).getBookingList().isEmpty()){
            errorList.add("Unable to delete customer due to active bookings");
        }else{
            customerService.deleteById(id);
        }
        model.addAttribute("errorList", errorList);
        return getCustomersFull(model);
    }

    @RequestMapping("/all")
    public String getCustomersFull(Model model){
        List<DetailedCustomerDTO> customerList = customerService.getAllCustomers();
        model.addAttribute("allCustomers", customerList);
        model.addAttribute("name","Customer name");
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
        List<String> errorList = new ArrayList<>();
        if (allBookings.isEmpty()){
            errorList.add("No Bookings Found");
        }
        model.addAttribute("allBookings", allBookings);
        model.addAttribute("bookingsHeader","Bookings by customer");
        model.addAttribute("bookingId","Booking ID:");
        model.addAttribute("roomId","Room ID:");
        model.addAttribute("from","From:");
        model.addAttribute("until","Until:");
        model.addAttribute("error", errorList);
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
                                 @RequestParam int numberOfPeople){
        if(roomService.isAvailable(roomId,startDate,endDate,numberOfPeople)){
            bookingService.createBooking(startDate,endDate,roomId,customerId,numberOfPeople);
        }
        else{
            System.out.println("False");
        }
        return "redirect:/customers/all";
    }

}
