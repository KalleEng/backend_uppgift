package com.example.backend_uppgift.Services;

import com.example.backend_uppgift.models.Blacklist;
import com.example.backend_uppgift.repositories.BlacklistRepo;

public interface BlacklistService {



    void saveBlacklist(Blacklist blacklist);

    boolean checkUserIfBlacklisted(String email);
}
