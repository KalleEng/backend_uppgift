package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.DTO.DetailedCustomerDTO;
import com.example.backend_uppgift.Services.CustomerService;
import com.example.backend_uppgift.repositories.CustomerRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerRepo customerRepo;

    public CustomerController(CustomerService customerService, CustomerRepo customerRepo) {
        this.customerService = customerService;
        this.customerRepo = customerRepo;
    }

    @RequestMapping("/get")
    public List<DetailedCustomerDTO> getCustomer(){
        return customerService.getAllCustomers();
    }

    @PostMapping("/updatename")
    public void updateCustomerName(@RequestParam String newVal,
                                   @RequestParam String oldVal){
        customerRepo.changeCustomerName(newVal,oldVal);
    }

    @PostMapping("/updateemail")
    public void updateCustomerEmail(@RequestParam String newVal,
                                    @RequestParam String oldVal){
        customerRepo.changeCustomerEmail(newVal,oldVal);
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
