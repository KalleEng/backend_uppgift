package com.example.backend_uppgift;

import com.example.backend_uppgift.Services.BlacklistService;
import com.example.backend_uppgift.models.Blacklist;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

@ComponentScan
public class FetchBlacklist implements CommandLineRunner {

    private final BlacklistService blacklistService;

    public FetchBlacklist(BlacklistService blacklistService) {
        this.blacklistService = blacklistService;
    }

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        Blacklist[] blacklists = objectMapper.readValue(new URL("https://javabl.systementor.se/api/stefan/blacklist"),
                Blacklist[].class);
        List<Blacklist> blacklistList = Arrays.asList(blacklists);
        for (Blacklist b: blacklistList) {
            blacklistService.saveBlacklist(new Blacklist(
                    b.id,
                    b.email,
                    b.name,
                    b.group,
                    b.created,
                    b.ok));
        }
    }
}
