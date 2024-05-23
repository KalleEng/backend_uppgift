package com.example.backend_uppgift.controllers;
import com.example.backend_uppgift.DTO.QueueViewModel;
import com.example.backend_uppgift.models.Queue;
import com.example.backend_uppgift.repositories.QueueRepo;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.UUID;

@Controller
public class QueueController extends BaseController{
    QueueRepo queueRepo;

    @GetMapping(path="/queues")
    String empty(Model model)
    {

        model.addAttribute("activeFunction", "queues");
        //setupVersion(model);

        var listOfViewModels = new ModelMapper().map(queueRepo.findAll(), QueueViewModel[].class);

        model.addAttribute("queues", listOfViewModels);
        return "queues";
    }



    @GetMapping(path="/queues/new")
    String Mew( Model model){
        model.addAttribute("queue", new Queue());
        model.addAttribute("activeFunction", "queues");
        return "newqueue";
    }
    @PostMapping(path="/queues/new")
    String SaveNew(@Valid Queue queue, BindingResult result, Model model){
        model.addAttribute("activeFunction", "queues");
        if(result.hasErrors()){
            return "newqueue";
        }
        var f = new Queue();
        f.setName(queue.getName());
        f.setRoomIdCSV(queue.getRoomIdCSV());
        f.setMessagesToSend(queue.getMessagesToSend());
        queueRepo.save(f);
        return "redirect:/queues";
    }


    @GetMapping(path="/queues/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    String Edit(@PathVariable("id") UUID id, Model model){
        model.addAttribute("activeFunction", "queues");

        var queue = queueRepo.findById(id).orElseThrow();
        model.addAttribute("queue", queue);
        model.addAttribute("activeFunction", "queues");
        return "editqueue";
    }
    @PostMapping(path="/queues/edit/{id}")
    String Save(@PathVariable("id") UUID id, @Valid Queue queue, BindingResult result, Model model){
        model.addAttribute("activeFunction", "queues");
        if(result.hasErrors()){
            return "editqueue";
        }
        var f = queueRepo.findById(id).orElseThrow();
        f.setName(queue.getName());
        f.setRoomIdCSV(queue.getRoomIdCSV());
        f.setMessagesToSend(queue.getMessagesToSend());
        queueRepo.save(f);
        return "redirect:/queues";
    }

}
