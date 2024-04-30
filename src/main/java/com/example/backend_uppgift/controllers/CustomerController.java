package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.DTO.DetailedCustomerDTO;
import com.example.backend_uppgift.Services.CustomerService;
import com.example.backend_uppgift.models.Customer;
import com.example.backend_uppgift.repositories.CustomerRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerRepo customerRepo;

    public CustomerController(CustomerService customerService, CustomerRepo customerRepo) {
        this.customerService = customerService;
        this.customerRepo = customerRepo;
    }

    @RequestMapping("/allWithDelete")
    public String getAllWithDelete(Model model){
        List<DetailedCustomerDTO> customerList = customerService.getAllCustomers();
        model.addAttribute("allCustomers", customerList);
        model.addAttribute("name", "Customer name");
        return "deleteCustomer";
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

}
