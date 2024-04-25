package com.example.backend_uppgift;

import com.example.backend_uppgift.controllers.RoomController;
import com.example.backend_uppgift.models.Booking;
import com.example.backend_uppgift.models.Customer;
import com.example.backend_uppgift.models.Room;
import com.example.backend_uppgift.repositories.BookingRepo;
import com.example.backend_uppgift.repositories.CustomerRepo;
import com.example.backend_uppgift.repositories.RoomRepo;
import jdk.dynalink.linker.support.Guards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;


@SpringBootApplication
public class BackendUppgiftApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendUppgiftApplication.class, args);
    }

    /*@Bean
    public CommandLineRunner demo(BookingRepo bookingRepo, CustomerRepo customerRepo, RoomRepo roomRepo){
        return (args -> {
            customerRepo.save(new Customer("Kai Honkonen","kai@example.com"));

            roomRepo.save(new Room(true,true,2));

            bookingRepo.save(new Booking(LocalDate.of(2024,12,11), LocalDate.of(2024,12,11),roomRepo.findById(1L).orElse(null),customerRepo.findById(1L).orElse(null)));
            bookingRepo.save(new Booking(LocalDate.of(2024,11,1), LocalDate.of(2024,11,11),roomRepo.findById(2L).orElse(null),customerRepo.findById(2L).orElse(null)));
            bookingRepo.save(new Booking(LocalDate.of(2024,10,12), LocalDate.of(2024,10,13),roomRepo.findById(3L).orElse(null),customerRepo.findById(3L).orElse(null)));

        });
    }*/

}

