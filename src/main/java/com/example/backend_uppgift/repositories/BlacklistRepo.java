package com.example.backend_uppgift.repositories;

import com.example.backend_uppgift.models.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistRepo extends JpaRepository<Blacklist, Long> {
}
