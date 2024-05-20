package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.DTO.DetailedBookingDTO;
import com.example.backend_uppgift.Services.BookingService;
import com.example.backend_uppgift.Services.ContractCustomerService;
import com.example.backend_uppgift.models.ContractCustomer;
import com.example.backend_uppgift.models.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/contract-customers")
public class ContractCustomerController {

    private final ContractCustomerService customerService;


    public ContractCustomerController(ContractCustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/all")
    public String getCustomersFull(Model model) {
        List<ContractCustomer> customerList = customerService.getAllCustomers();
        model.addAttribute("allCustomers", customerList);
        model.addAttribute("name", "Customer name");
        return "getContractCustomersFull";
    }


}
