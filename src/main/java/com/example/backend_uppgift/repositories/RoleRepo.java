package com.example.backend_uppgift.repositories;

import com.example.backend_uppgift.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RoleRepo extends CrudRepository<Role, UUID> {
    Role findByName(String name);
}
