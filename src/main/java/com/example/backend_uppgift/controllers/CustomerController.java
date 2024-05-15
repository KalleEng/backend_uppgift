package com.example.backend_uppgift.controllers;
import com.example.backend_uppgift.DTO.DetailedBookingDTO;
import com.example.backend_uppgift.DTO.DetailedCustomerDTO;
import com.example.backend_uppgift.Services.BookingService;
import com.example.backend_uppgift.Services.CustomerService;
import com.example.backend_uppgift.Services.RoomService;
import com.example.backend_uppgift.models.Customer;
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
    public String editCustomer(@PathVariable Long id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        model.addAttribute("name", "customerName");
        return "updateCustomerForm";
    }

    @PostMapping("/update")
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
    public String addCustomer(Model model) {
        return "addNewCustomer";
    }

    @RequestMapping("/added")
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
                                 Model model) {

        List<String> errorList = new ArrayList<>();
        if (startDate == null || endDate == null || roomId == null) {
            errorList.add("Fields can't be empty.");
            model.addAttribute("errorList", errorList);
            return getCustomersFull(model);
        } else {
            if (endDate.isBefore(startDate)){
                errorList.add("End Date can't be before Start Date");
            }
            if (roomService.getRoomById(roomId) == null) {
                errorList.add("Room doesn't exist");
            }
            if (numberOfPeople > roomService.getRoomById(roomId).getBedCapacity()) {
                errorList.add("Room is too small. Choose one with bigger capacity");
            } else {
                if (roomService.isAvailable(roomId, startDate, endDate, numberOfPeople)) {
                    bookingService.createBooking(startDate, endDate, roomId, customerId, numberOfPeople);
                } else {
                    errorList.add("Room not available for selected dates");
                }
            }
        }
        model.addAttribute("errorList", errorList);
        return getCustomersFull(model);
    }

}
