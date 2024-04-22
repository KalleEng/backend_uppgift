package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.models.Room;
import com.example.backend_uppgift.repositories.BookingRepo;
import com.example.backend_uppgift.repositories.CustomerRepo;
import com.example.backend_uppgift.repositories.RoomRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    private final CustomerRepo customerRepo;
    private final BookingRepo bookingRepo;
    private final RoomRepo roomRepo;

    public RoomController(CustomerRepo customerRepo, BookingRepo bookingRepo, RoomRepo roomRepo) {
        this.customerRepo = customerRepo;
        this.bookingRepo = bookingRepo;
        this.roomRepo = roomRepo;
    }

    @PostMapping("/create")
    public String createRoom(@RequestParam boolean isAvailable,
                             @RequestParam boolean isDoubleRoom,
                             @RequestParam int numberOfBeds){
        roomRepo.save(new Room(isAvailable,isAvailable,numberOfBeds));
        return "Room created";
    }

    @RequestMapping("/get")
    public List<Room> getRooms(){
        return roomRepo.findAll();
    }
}
