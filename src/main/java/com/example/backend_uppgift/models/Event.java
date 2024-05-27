package com.example.backend_uppgift.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private String personnelName;

    public Event(int roomId, String event, LocalDateTime timeStamp, String personnelName) {
        this.roomId = roomId;
        this.event = event;
        this.timeStamp = timeStamp;
        this.personnelName = personnelName;
    }
}
