package com.example.backend_uppgift.Configurations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RabbitMQProperties {
    private String host;
    private String username;
    private String password;
    private String queueName;
}
