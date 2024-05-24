package com.example.backend_uppgift;

import com.example.backend_uppgift.Services.ShipperService;
import com.example.backend_uppgift.Utils.StreamProvider;
import com.example.backend_uppgift.models.Shipper;
import com.example.backend_uppgift.repositories.ShipperRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@ComponentScan
public class FetchShippers implements CommandLineRunner {
    ShipperService shipperService;
    public final StreamProvider streamProvider;

    public FetchShippers(ShipperService shipperService, StreamProvider streamProvider) {
        this.shipperService = shipperService;
        this.streamProvider = streamProvider;
    }

    @Override
    public void run(String... args) throws Exception{

        List<Shipper> shippersList = getShippers();
        for (Shipper s: shippersList) {
            shipperService.saveShipper(new Shipper(s.id,s.companyName, s.phone));
        }

    }
    public List<Shipper> getShippers() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return Arrays.asList(objectMapper.readValue(streamProvider.getDataStreamShippers(), Shipper[].class));
    }
}
