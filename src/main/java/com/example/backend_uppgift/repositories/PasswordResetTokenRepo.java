package com.example.backend_uppgift.repositories;

import com.example.backend_uppgift.models.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PasswordResetTokenRepo extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);

    void deleteByExpiryDateLessThan(LocalDateTime expiryDate);
}
