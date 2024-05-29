package com.example.backend_uppgift.repositories;

import com.example.backend_uppgift.models.EmailTemplate;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmailRepo extends JpaRepository<EmailTemplate,Long> {

    @Modifying
    @Transactional
    @Query("UPDATE EmailTemplate e SET e.htmlTemplate = :body WHERE e.id = :id")
    int updateEmailBodyById(@Param("id") Long id, @Param("body") String body);
}
