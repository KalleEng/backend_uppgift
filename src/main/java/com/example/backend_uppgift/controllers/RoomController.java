package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.DTO.DetailedRoomDTO;
import com.example.backend_uppgift.Services.RoomService;
import com.example.backend_uppgift.models.Customer;
import com.example.backend_uppgift.models.Room;
import com.example.backend_uppgift.repositories.RoomRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @RequestMapping("/all")
    public String showAllRooms(Model model){
        List<DetailedRoomDTO> allRooms = roomService.getAllRooms();
        model.addAttribute("allRooms", allRooms);
        model.addAttribute("roomsHeader", "All Rooms");
        model.addAttribute("roomId", "Room ID:");
        model.addAttribute("bedCap", "Bed Capacity:");
        model.addAttribute("price", "Price Per Night:");
        return "getRooms";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id, Model model){
        roomService.deleteById(id);
        return showAllRooms(model);
    }

    @RequestMapping("/create")
    public String createRoom(){
        return "createRoom";
    }

    @RequestMapping("/created")
    public String createdRoom(@RequestParam int bedCapacity,
                              @RequestParam double price){
        roomService.saveRoom(new Room(bedCapacity,price));
        return "redirect:/rooms/all";
    }

}
