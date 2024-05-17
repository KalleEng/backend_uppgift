package com.example.backend_uppgift;
import com.example.backend_uppgift.models.Booking;
import com.example.backend_uppgift.models.Customer;
import com.example.backend_uppgift.models.Room;
import com.example.backend_uppgift.repositories.BookingRepo;
import com.example.backend_uppgift.repositories.CustomerRepo;
import com.example.backend_uppgift.repositories.RoomRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Objects;

@SpringBootApplication
public class BackendUppgiftApplication {
    public static void main(String[] args) {
        if (args.length == 0){
            SpringApplication.run(BackendUppgiftApplication.class, args);
        } else if (Objects.equals(args[0],"fetchContractCustomers")) {
            SpringApplication application = new SpringApplication(FetchContractCustomers.class);
            application.setWebApplicationType(WebApplicationType.NONE);
            application.run(args);
        } else if (Objects.equals(args[0],"fetchShippers")) {
            SpringApplication application = new SpringApplication(FetchShippers.class);
            application.setWebApplicationType(WebApplicationType.NONE);
            application.run(args);
        } else if (Objects.equals(args[0],"seedToDatabase")) {
            SpringApplication application = new SpringApplication(SeedToDatabase.class);
            application.setWebApplicationType(WebApplicationType.NONE);
            application.run(args);
        }
    }




/*

   @Bean
    public CommandLineRunner demo(BookingRepo bookingRepo, CustomerRepo customerRepo, RoomRepo roomRepo){
        return (args -> {
            customerRepo.save(new Customer("Kai Honkonen","kai@example.com"));
            customerRepo.save(new Customer("Kalle Engwall","kalle@example.com"));
            customerRepo.save(new Customer("Jakob Engwall", "jakob.example.com"));
            customerRepo.save(new Customer("Hidro Koureks","hidro@example.com"));

          roomRepo.save(new Room(1));
          roomRepo.save(new Room(2));
          roomRepo.save(new Room(3));
          roomRepo.save(new Room(4));

        });
    }
*/

}

