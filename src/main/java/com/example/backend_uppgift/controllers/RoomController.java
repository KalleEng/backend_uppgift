package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.DTO.DetailedRoomDTO;
import com.example.backend_uppgift.Services.RoomService;
import com.example.backend_uppgift.models.Room;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

/*    @PostMapping("/create")
    public String createRoom(@RequestParam boolean isAvailable,
                             @RequestParam boolean isDoubleRoom,
                             @RequestParam int numberOfBeds){
        roomRepo.save(new Room(isAvailable,isAvailable,numberOfBeds));
        return "Room created";
    }*/

    @RequestMapping("/get")
    public List<DetailedRoomDTO> getRooms(){
        return roomService.getAllRooms();
    }

    @RequestMapping("/available")
    public Boolean checkAvailability(@RequestParam Long roomId,
                                     @RequestParam LocalDate startDate,
                                     @RequestParam LocalDate endDate){
        return roomService.isAvailable(roomId, startDate, endDate);
    }
}
