package com.example.backend_uppgift.models;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("RoomNo")
    private int roomId;
    private String type;
    @JsonProperty("TimeStamp")
    private LocalDateTime timeStamp;
    @JsonProperty("CleaningByUser")
    private String personnelName;

    public Event(int roomId, String type, LocalDateTime timeStamp, String personnelName) {
        this.roomId = roomId;
        this.type = type;
        this.timeStamp = timeStamp;
        this.personnelName = personnelName;
    }
}
