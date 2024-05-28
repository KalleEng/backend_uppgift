package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.DTO.DetailedBookingDTO;
import com.example.backend_uppgift.Services.BookingService;
import com.example.backend_uppgift.Services.ContractCustomerService;
import com.example.backend_uppgift.Services.Impl.ContractCustomerServiceImpl;
import com.example.backend_uppgift.models.ContractCustomer;
import com.example.backend_uppgift.models.Customer;
import com.example.backend_uppgift.repositories.ContractCustomerRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/contract-customers")
@PreAuthorize("isAuthenticated()")
public class ContractCustomerController {

    /*
    ContractCustomerRepo customerRepo;

    public ContractCustomerController(ContractCustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }
     */

    private final ContractCustomerServiceImpl customerService;


    public ContractCustomerController(ContractCustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    /*
    @RequestMapping("/all")
    public String getCustomersFull(Model model) {
        List<ContractCustomer> customerList = customerService.getAllCustomers();
        model.addAttribute("allCustomers", customerList);
        model.addAttribute("name", "Customer name");
        return "getContractCustomersFull";
    }
     */

    @GetMapping(path = "/all")
    String empty(Model model, @RequestParam(defaultValue = "1") int pageNo,
                 @RequestParam(defaultValue = "10") int pageSize,
                 @RequestParam(defaultValue = "companyName") String sortCol,
                 @RequestParam(defaultValue = "asc") String sortOrder,
                 @RequestParam(defaultValue = "") String q) {

        //model.addAttribute("activeFunction", "home");

        q = q.trim();

        model.addAttribute("q", q);
        model.addAttribute("sortCol", sortCol);
        model.addAttribute("sortOrder", sortOrder);

        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortCol);
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        if (!q.isEmpty()) {
            //model.addAttribute("customers", customerService.findAllByCompanyNameContains(q, sort));
            List<ContractCustomer> customerList = customerService.findAllByCompanyNameContains(q, sort);
            model.addAttribute("allCustomers", customerList);
            model.addAttribute("totalPages", 1);
            model.addAttribute("pageNo", 1);
        } else {
            List<ContractCustomer> customerList = customerService.findAll(sort);
            model.addAttribute("allCustomers", customerList);
            model.addAttribute("name", "Customer name");

            model.addAttribute("totalPages", 1);
            model.addAttribute("pageNo", pageNo);
            model.addAttribute("customers", customerList);
        }
        return "getContractCustomersFull";
    }
}
