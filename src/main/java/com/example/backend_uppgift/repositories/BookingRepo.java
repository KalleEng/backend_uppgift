package com.example.backend_uppgift.repositories;

import com.example.backend_uppgift.models.Booking;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findByRoomId(Long roomId);

    @Query("select b from Booking b where b.startDate <= :endDate and b.endDate >= :startDate and b.room.bedCapacity >= :numberOfPeople")
    List<Booking> findByDateRange(LocalDate startDate, LocalDate endDate, int numberOfPeople);
}
