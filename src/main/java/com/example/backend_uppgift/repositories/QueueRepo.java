package com.example.backend_uppgift.repositories;

import com.example.backend_uppgift.models.Queue;
import org.springframework.data.repository.CrudRepository;


import java.util.UUID;

public interface QueueRepo extends CrudRepository<Queue, UUID> {
}
