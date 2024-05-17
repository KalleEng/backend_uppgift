package com.example.backend_uppgift.repositories;

import com.example.backend_uppgift.models.Event;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepo extends JpaRepository<Event, Long> {
    List<Event> findByRoomId(int roomId);
}
