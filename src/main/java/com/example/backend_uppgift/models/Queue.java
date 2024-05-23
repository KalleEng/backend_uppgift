package com.example.backend_uppgift.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomIdCSV() {
        return roomIdCSV;
    }

    public void setRoomIdCSV(String roomIdCSV) {
        this.roomIdCSV = roomIdCSV;
    }

    public int getMessagesToSend() {
        return messagesToSend;
    }

    public void setMessagesToSend(int messagesToSend) {
        this.messagesToSend = messagesToSend;
    }

    @Column(name = "Name")
    private String name;
    @Column(name = "RoomIdCSV")
    private String roomIdCSV;
    @Column(name = "MessagesToSend")
    private int messagesToSend;
}
