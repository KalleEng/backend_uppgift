package com.example.backend_uppgift.repositories;

import com.example.backend_uppgift.DTO.DetailedRoomDTO;
import com.example.backend_uppgift.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoomRepo extends JpaRepository<Room, Long> {

    @Query("SELECT r from Room r where r.id = :roomId")
    Optional<Room> getRoomById(@Param("roomId") Long roomId);
}
