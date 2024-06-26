package com.example.backend_uppgift.controllers;
import com.example.backend_uppgift.DTO.DetailedBookingDTO;
import com.example.backend_uppgift.DTO.DetailedCustomerDTO;
import com.example.backend_uppgift.Services.BookingService;
import com.example.backend_uppgift.Services.CustomerService;
import com.example.backend_uppgift.Services.DiscountService;
import com.example.backend_uppgift.Services.Impl.EmailService;
import com.example.backend_uppgift.Services.RoomService;
import com.example.backend_uppgift.Utils.Blacklist;
import com.example.backend_uppgift.models.Customer;
import com.example.backend_uppgift.repositories.CustomerRepo;
import com.example.backend_uppgift.repositories.ShipperRepo;
import jakarta.mail.MessagingException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customers")
@PreAuthorize("isAuthenticated()")

public class CustomerController {
    private final CustomerService customerService;
    private final BookingService bookingService;
    private final Blacklist blacklistCheck;
    private final RoomService roomService;
    private final DiscountService discountService;
    private final EmailService emailService;

    public CustomerController(CustomerService customerService, BookingService bookingService, Blacklist blacklistCheck, RoomService roomService, CustomerRepo customerRepo, ShipperRepo shipperRepo, DiscountService discountService, EmailService emailService) {
        this.customerService = customerService;
        this.bookingService = bookingService;
        this.blacklistCheck = blacklistCheck;
        this.roomService = roomService;
        this.discountService = discountService;
        this.emailService = emailService;
    }

    @RequestMapping("/test")
    public String test(){
        System.out.println("Test");
        return "test";
    }

    @RequestMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public String deleteCustomer(@PathVariable Long id, Model model) {
        List<String> errorList = new ArrayList<>();
        if (!customerService.findById(id).getBookingList().isEmpty()) {
            errorList.add("Unable to delete customer due to active bookings");
        } else {
            customerService.deleteById(id);
        }
        model.addAttribute("errorList", errorList);
        return getCustomersFull(model);
    }

    @RequestMapping("/all")
    public String getCustomersFull(Model model) {
        List<DetailedCustomerDTO> customerList = customerService.getAllCustomers();
        model.addAttribute("allCustomers", customerList);
        model.addAttribute("name", "Customer name");
        return "getCustomersFull";
    }


    @RequestMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public String editCustomer(@PathVariable Long id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        model.addAttribute("name", "customerName");
        return "updateCustomerForm";
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('Admin')")
    public String saveEditedCustomer(Model model, Customer customer) {
        customerService.saveCustomer(customer);
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("allCustomers", customerList);
        model.addAttribute("name", "Customer name");
        return "getCustomersFull";
    }

    @RequestMapping("/customerBookings/{id}")
    public String showCustomerBookings(@PathVariable Long id, Model model) {
        List<DetailedBookingDTO> allBookings = bookingService.getBookingsByCustomerId(id);
        String customerName = customerService.findById(id).getName();
        List<String> errorList = new ArrayList<>();
        if (allBookings.isEmpty()) {
            errorList.add("No bookings for customer found.");
        }
        model.addAttribute("allBookings", allBookings);
        model.addAttribute("bookingsHeader", "Bookings by ");
        model.addAttribute("bookingId", "Booking ID:");
        model.addAttribute("roomId", "Room ID:");
        model.addAttribute("from", "From:");
        model.addAttribute("until", "Until:");
        model.addAttribute("error", errorList);
        model.addAttribute("customerName", customerName);
        return "showAllBookings";
    }

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('Admin')")
    public String addCustomer(Model model) {
        return "addNewCustomer";
    }

    @RequestMapping("/added")
    @PreAuthorize("hasAuthority('Admin')")
    public String addedCustomer(@RequestParam String name,
                                @RequestParam String email, Model model) {
        List<String> errorList = new ArrayList<>();
        if (name.isEmpty() || email.isEmpty()) {
            errorList.add("Fields can't be empty.");
            model.addAttribute("errorList", errorList);
            return addCustomer(model);
        } else {
            customerService.saveCustomer(new Customer(name, email));
        }
        return "redirect:/customers/all";
    }

    @RequestMapping("/createBooking/{id}")
    public String createBooking(@PathVariable Long id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        model.addAttribute("name", "customerName");

        return "newBooking";
    }

    @PostMapping("/confirmBooking")
    public String confirmBooking(@RequestParam(required = false) LocalDate startDate,
                                 @RequestParam(required = false) LocalDate endDate,
                                 @RequestParam(required = false) Long roomId,
                                 @RequestParam Long customerId,
                                 @RequestParam(required = false, defaultValue = "1") int numberOfPeople,
                                 Model model) throws IOException, InterruptedException, MessagingException {

        double total = discountService.calculateTotal(startDate, endDate, roomService.getRoomById(roomId).getPrice(), customerId);
        List<String> errorList = new ArrayList<>();
        Map<String, Object> modelToEmailTemplate = new HashMap<>();

        if (startDate == null || endDate == null || roomId == null) {
            errorList.add("Fields can't be empty.");
            model.addAttribute("errorList", errorList);
            return getCustomersFull(model);
        } else {
            if (endDate.isBefore(startDate)) {
                errorList.add("End Date can't be before Start Date");
            }
            if (roomService.getRoomById(roomId) == null) {
                errorList.add("Room doesn't exist");
            } else {

                if (numberOfPeople > roomService.getRoomById(roomId).getBedCapacity()) {
                    errorList.add("Room is too small. Choose one with bigger capacity");
                } else {
                    if (roomService.isAvailable(roomId, startDate, endDate, numberOfPeople)) {
                        if (blacklistCheck.isOk(customerService.findById(customerId).getEmail())) {
                            bookingService.createBooking(startDate, endDate, roomId, customerId, numberOfPeople, total);

                        } else {
                            errorList.add("User is blacklisted");
                        }
                    } else {
                        errorList.add("Room not available for selected dates");
                    }
                }
            }
        }
        model.addAttribute("errorList", errorList);
        if (!errorList.isEmpty()) {
            return "getCustomersFull";
        } else {
            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
            modelToEmailTemplate.put("customerName", customerService.findById(customerId).getName());
            modelToEmailTemplate.put("customerEmail", customerService.findById(customerId).getEmail());
            modelToEmailTemplate.put("startDate", startDate);
            modelToEmailTemplate.put("endDate", endDate);
            modelToEmailTemplate.put("numberOfBookedDays", daysBetween);
            modelToEmailTemplate.put("numberOfPeople", numberOfPeople);
            emailService.sendEmail(customerService.findById(customerId).getEmail(), modelToEmailTemplate);
            return "redirect:/customers/all";
        }
    }

}
