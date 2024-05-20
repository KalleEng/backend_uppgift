package com.example.backend_uppgift.repositories;

import com.example.backend_uppgift.models.Booking;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findByRoomId(Long roomId);

    @Query("select b from Booking b where b.startDate <= :endDate and b.endDate >= :startDate and b.room.bedCapacity >= :numberOfPeople")
    List<Booking> findByDateRange(LocalDate startDate, LocalDate endDate, int numberOfPeople);

    @Query("SELECT case when count (b) = 0 then true else false END " +
    "FROM Booking b JOIN b.room r " +
    "WHERE b.room.id = :roomId " +
    "AND r.bedCapacity >= :numberOfPeople " +
    "AND NOT (b.endDate < :startDate or b.startDate > :endDate)")
    boolean isAvailable(@Param("roomId") Long roomId,
                        @Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate,
                        @Param("numberOfPeople") int numberOfPeople);
}
