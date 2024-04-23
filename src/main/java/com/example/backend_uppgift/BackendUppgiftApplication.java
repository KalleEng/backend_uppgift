package com.example.backend_uppgift;

import com.example.backend_uppgift.controllers.RoomController;
import jdk.dynalink.linker.support.Guards;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;






@SpringBootApplication
public class BackendUppgiftApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendUppgiftApplication.class, args);
    }

}

