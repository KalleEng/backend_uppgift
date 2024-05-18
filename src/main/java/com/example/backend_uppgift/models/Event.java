package com.example.backend_uppgift.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue
    private Long id;
    private int roomId;
    private String event;
    private LocalDateTime timeStamp;
    private String personelName;

    public Event(int roomId, String event, LocalDateTime timeStamp, String personelName) {
        this.roomId = roomId;
        this.event = event;
        this.timeStamp = timeStamp;
        this.personelName = personelName;
    }
}
