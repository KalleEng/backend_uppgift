package com.example.backend_uppgift;

import com.example.backend_uppgift.Services.EventService;
import com.example.backend_uppgift.Utils.StreamProvider;
import com.example.backend_uppgift.models.Event;
import com.example.backend_uppgift.repositories.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.logging.Logger;

@ComponentScan
public class SeedToDatabase implements CommandLineRunner {

    private static final Logger logger = Logger.getLogger(SeedToDatabase.class.getName());


    private EventService eventService;


    public SeedToDatabase(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Seeding database");
        eventService.saveAll(Arrays.asList(
                new Event(5, "Dörren öppnad", LocalDateTime.of(2022, 12, 12, 18, 11, 4), null),
                new Event(5, "Dörren stängd", LocalDateTime.of(2022, 12, 12, 18, 11, 6), null),
                new Event(3, "Dörren öppnad", LocalDateTime.of(2022, 12, 13, 10, 11, 4), null),
                new Event( 3, "Städning påbörjat", LocalDateTime.of(2022, 12, 13, 10, 11, 6), "Per Persson"),
                new Event( 3, "Dörren stängd", LocalDateTime.of(2022, 12, 12, 10, 11, 14), null),
                new Event( 3, "Städning avslutat", LocalDateTime.of(2022, 12, 13, 12, 11, 6), "Per Persson")
        ));
    }
}