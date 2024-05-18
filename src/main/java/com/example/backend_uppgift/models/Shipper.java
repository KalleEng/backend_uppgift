package com.example.backend_uppgift.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Shipper {
    @Id
    public int id;
    @Transient
    public String email;
    public String companyName;
    @Transient
    public String contactName;
    @Transient
    public String contactTitle;
    @Transient
    public String streetAddress;
    @Transient
    public String city;
    @Transient
    public String postalCode;
    @Transient
    public String country;
    public String phone;
    @Transient
    public String fax;


    public Shipper(int id, String companyName, String phone) {
        this.id = id;
        this.companyName = companyName;
        this.phone = phone;
    }
}

