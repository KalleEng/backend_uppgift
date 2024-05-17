package com.example.backend_uppgift.Services;

import com.example.backend_uppgift.models.Event;

import java.util.List;

public interface EventService {
    void saveEvent(Event event);

    void saveAll(List<Event> list);

    List<Event> getEventsByRoomId(int roomId);
}
