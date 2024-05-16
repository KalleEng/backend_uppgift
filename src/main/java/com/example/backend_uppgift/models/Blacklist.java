package com.example.backend_uppgift.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Blacklist {
    @Id
    public int id;
    public String email;
    public String name;
    @Transient
    public String group;
    public Date created;
    public boolean ok;
}
