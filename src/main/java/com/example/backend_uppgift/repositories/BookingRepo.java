package com.example.backend_uppgift.repositories;

import com.example.backend_uppgift.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findByRoomId(Long roomId);
}
