package com.example.backend_uppgift.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EmailTemplate {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 65535)
    private String htmlTemplate;


    public EmailTemplate(String htmlTemplate) {
        this.htmlTemplate = htmlTemplate;
    }
}
