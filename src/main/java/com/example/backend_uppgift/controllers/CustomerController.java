package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.DTO.DetailedCustomerDTO;
import com.example.backend_uppgift.Services.CustomerService;
import com.example.backend_uppgift.models.Customer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /*@PostMapping("/add")
    public String addCustomer(@RequestParam String name, @RequestParam String email){
        customerRepo.save(new Customer(name, email));
        return "Saved customer ";
    }*/

    @RequestMapping("/get")
    public List<DetailedCustomerDTO> getCustomer(){
        return customerService.getAllCustomers();
    }

/*    @PostMapping("/addbooking")
    public String addBooking(@RequestParam LocalDate startDate,
                             @RequestParam LocalDate endDate,
                             @RequestParam Long roomId,
                             @RequestParam Long customerId){
        bookingRepo.save(new Booking(startDate, endDate,
                roomRepo.findById(roomId).orElse(null),
                customerRepo.findById(customerId).orElse(null)));
        return "Booking added";
    }*/
}
