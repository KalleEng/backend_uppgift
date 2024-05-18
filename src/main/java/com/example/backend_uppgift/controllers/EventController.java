package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.Services.EventService;
import com.example.backend_uppgift.models.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    @Autowired
    public EventController( EventService eventService) {
        this.eventService = eventService;
    }

    /*@RequestMapping("/eventByRoomId/{roomId}")
    public List<Event> getEventsByRoomId(@PathVariable int roomId){
        return eventRepo.findByRoomId(roomId);
    }*/

    @GetMapping("/eventByRoomId")
    public String getEventsByRoomId(@RequestParam int roomId, Model model){
        List<Event> events = eventService.getEventsByRoomId(roomId);
        model.addAttribute("events", events);
        model.addAttribute("roomId", roomId);
        return "viewRoomEvent";
    }

}
