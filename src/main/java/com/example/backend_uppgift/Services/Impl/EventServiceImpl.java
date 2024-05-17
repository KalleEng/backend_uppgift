package com.example.backend_uppgift.Services.Impl;

import com.example.backend_uppgift.Services.EventService;
import com.example.backend_uppgift.models.Event;
import com.example.backend_uppgift.repositories.EventRepo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventServiceImpl implements EventService {
    private EventRepo eventRepo;

    public EventServiceImpl(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    @Override
    public void saveEvent(Event event) {
        eventRepo.save(event);
    }

    @Override
    public void saveAll(List<Event> list) {
        eventRepo.saveAll(list);
    }

    @Override
    public List<Event> getEventsByRoomId(int roomId) {
        return eventRepo.findByRoomId(roomId);
    }
}
