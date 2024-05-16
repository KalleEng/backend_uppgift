package com.example.backend_uppgift;

import com.example.backend_uppgift.Services.ShipperService;
import com.example.backend_uppgift.models.Shipper;
import com.example.backend_uppgift.repositories.ShipperRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

@ComponentScan
public class FetchShippers implements CommandLineRunner {
    private final ShipperService shipperService;

    public FetchShippers(ShipperService shipperService) {
        this.shipperService = shipperService;
    }

    @Override
    public void run(String... args) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        Shipper[] shippers = objectMapper.readValue(new URL("https://javaintegration.systementor.se/shippers"), Shipper[].class);
        List<Shipper> shippersList = Arrays.asList(shippers);
        for (Shipper s: shippersList) {
            shipperService.saveShipper(new Shipper(s.id,s.companyName, s.phone));
        }

    }
}
