package com.example.backend_uppgift.repositories;

import com.example.backend_uppgift.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room, Long> {
}
