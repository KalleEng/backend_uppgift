package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.DTO.DetailedBookingDTO;
import com.example.backend_uppgift.DTO.DetailedCustomerDTO;
import com.example.backend_uppgift.Services.BookingService;
import com.example.backend_uppgift.Services.CustomerService;
import com.example.backend_uppgift.models.Customer;
import com.example.backend_uppgift.repositories.CustomerRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final BookingService bookingService;
    private final CustomerRepo customerRepo;

    public CustomerController(CustomerService customerService, BookingService bookingService, CustomerRepo customerRepo) {
        this.customerService = customerService;
        this.bookingService = bookingService;
        this.customerRepo = customerRepo;
    }

    @RequestMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id, Model model){
        customerRepo.deleteById(id);
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
    public String createCustomer(@PathVariable Long id,Model model){
        Customer customer = customerRepo.findById(id).get();
        model.addAttribute("customer",customer);
        model.addAttribute("name","customerName");
        return "updateCustomerForm";
    }

    @PostMapping("/update")
    public String addCustomer(Model model, Customer customer){
        customerRepo.save(customer);
        List<Customer> customerList = customerRepo.findAll();
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
        customerRepo.save(new Customer(name,email));
        return "redirect:/customers/all";
    }

}
