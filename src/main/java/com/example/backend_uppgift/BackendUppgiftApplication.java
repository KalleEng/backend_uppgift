package com.example.backend_uppgift;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendUppgiftApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendUppgiftApplication.class, args);
    }

/*    @Bean
    public CommandLineRunner demo(BookingRepo bookingRepo, CustomerRepo customerRepo, RoomRepo roomRepo){
        return (args -> {
            customerRepo.save(new Customer("Kalle Engwall","kalle@example.com"));
            customerRepo.save(new Customer("Jakob Engwall", "jakob.example.com"));
            customerRepo.save(new Customer("Hidro Koureks","hidro@example.com"));

            roomRepo.save(new Room(true,true,2));
            roomRepo.save(new Room(true,false,1));
            roomRepo.save(new Room(true,true,1));

            bookingRepo.save(new Booking(LocalDate.of(2024,12,11), LocalDate.of(2024,12,11),roomRepo.findById(1L).orElse(null),customerRepo.findById(1L).orElse(null)));
            bookingRepo.save(new Booking(LocalDate.of(2024,11,1), LocalDate.of(2024,11,11),roomRepo.findById(2L).orElse(null),customerRepo.findById(2L).orElse(null)));
            bookingRepo.save(new Booking(LocalDate.of(2024,10,12), LocalDate.of(2024,10,13),roomRepo.findById(3L).orElse(null),customerRepo.findById(3L).orElse(null)));

        });
    }*/

}

