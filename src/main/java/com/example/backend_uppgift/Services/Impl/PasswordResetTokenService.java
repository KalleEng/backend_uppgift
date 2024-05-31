package com.example.backend_uppgift.Services.Impl;

import com.example.backend_uppgift.models.PasswordResetToken;
import com.example.backend_uppgift.repositories.PasswordResetTokenRepo;
import com.example.backend_uppgift.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetTokenService {
    private static final int EXPIRATION_HOURS = 24;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordResetTokenRepo tokenRepository;

    public void createToken(User user) {
        String tokenValue = UUID.randomUUID().toString();
        LocalDateTime expiryDate = LocalDateTime.now().plusHours(EXPIRATION_HOURS);
        PasswordResetToken token = new PasswordResetToken(tokenValue, user, expiryDate, false);
        tokenRepository.save(token);
        System.out.println(expiryDate);
        String resetLink = "http://localhost:8080/reset-password/" + tokenValue;
        emailService.sendSimpleEmail(user.getUsername(), "Password Reset", "Follow the link to reset your password: " + resetLink);
    }


    public void deleteToken(PasswordResetToken token) {
        tokenRepository.delete(token);
    }

    public PasswordResetToken getToken(String tokenValue) {
        return tokenRepository.findByToken(tokenValue).orElse(null);
    }

    public boolean isTokenExpired(PasswordResetToken token) {
        return token.getExpiryDate().isBefore(LocalDateTime.now());
    }

    public void deleteExpiredTokens() {
        LocalDateTime now = LocalDateTime.now();
        tokenRepository.deleteByExpiryDateLessThan(now);
    }
}
