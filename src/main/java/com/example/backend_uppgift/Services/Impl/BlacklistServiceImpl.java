package com.example.backend_uppgift.Services.Impl;

import com.example.backend_uppgift.Services.BlacklistService;
import com.example.backend_uppgift.models.Blacklist;
import com.example.backend_uppgift.repositories.BlacklistRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlacklistServiceImpl implements BlacklistService {
    BlacklistRepo blacklistRepo;

    public BlacklistServiceImpl(BlacklistRepo blacklistRepo){
        this.blacklistRepo = blacklistRepo;
    }
    @Override
    public void saveBlacklist(Blacklist blacklist) {
        blacklistRepo.save(blacklist);
    }

    @Override
    public boolean checkUserIfBlacklisted(String email){
        Optional<Blacklist> blackListUser;
        if((blackListUser = blacklistRepo.findAll().stream().filter(u -> u.email.equals(email)).findFirst()).isPresent()){
            return blackListUser.get().ok;
        } else{
            return true;
        }
    }
}
