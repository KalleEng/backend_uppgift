package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.DTO.DetailedCustomerDTO;
import com.example.backend_uppgift.Services.CustomerService;
import com.example.backend_uppgift.models.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public String registerCustomer(@RequestParam String name, @RequestParam String email){
        customerService.registerCustomer(name, email);
        return "Customer registered successfully";
    }

    @PutMapping("/update/{customerId}")
    public String updateCustomer(@PathVariable Long customerId,
                                 @RequestParam String name,
                                 @RequestParam String email) {
        customerService.updateCustomer(customerId, name, email);
        return "Customer details updated successfully";
    }

    @DeleteMapping("/delete/{customerId}")
    public String deleteCustomer(@PathVariable Long customerId) {
        if (customerService.hasBookings(customerId)) {
            return "Cannot delete customer. Bookings are associated with this customer.";
        } else {
            customerService.deleteCustomer(customerId);
            return "Customer deleted successfully";
        }
    }

    @RequestMapping("/get")
    public List<DetailedCustomerDTO> getCustomers() {
        return customerService.getAllCustomers();
    }
}
