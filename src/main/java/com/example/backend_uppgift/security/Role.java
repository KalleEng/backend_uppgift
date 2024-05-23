package com.example.backend_uppgift.security;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Table(name="Role")
@NoArgsConstructor
@Entity
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    private String name;

}
