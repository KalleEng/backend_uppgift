package com.example.backend_uppgift.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecTestController extends BaseController{

    @GetMapping(path = "/admin")
    @PreAuthorize("hasAuthority('Admin')")
    public String empty(Model model){
        model.addAttribute("activeFunction","queues");
        model.addAttribute("page","Admin");
        return "security/admin";
    }

    /*@GetMapping(path = "/customer")
    @PreAuthorize("hasAuthority('Customer')")
    public String mew(Model model){
        model.addAttribute("")
        model.addAttribute("username")
    }*/
}

