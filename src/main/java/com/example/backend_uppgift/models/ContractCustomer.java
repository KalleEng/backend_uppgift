package com.example.backend_uppgift.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JacksonXmlRootElement(localName = "customers")
public class ContractCustomer {
    @Id
    public int id;

    @Column(name = "companyName")
    public String companyName;

    @Column(name = "contactName")
    public String contactName;

    @Column(name = "contactTitle")
    public String contactTitle;

    @Column(name = "streetAddress")
    public String streetAddress;

    @Column(name = "city")
    public String city;

    @Column(name = "postalCode")
    public int postalCode;

    @Column(name = "country")
    public String country;

    @Column(name = "phone")
    public String phone;

    @Column(name = "fax")
    public String fax;
}
