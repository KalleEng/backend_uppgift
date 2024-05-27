package com.example.backend_uppgift;

import com.example.backend_uppgift.Configurations.IntegrationProperties;
import com.example.backend_uppgift.Utils.StreamProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.r2dbc.ConnectionFactoryBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer implements CommandLineRunner {

    @Autowired
    private StreamProvider streamProvider;

    @Override
    public void run(String... args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(streamProvider.getRabbitHost());
        factory.setUsername(streamProvider.getRabbitUsername());
        factory.setPassword(streamProvider.getRabbitPassword());
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String queueName = streamProvider.getRabbitQueueName();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        System.out.println("[*] Waiting for messages. To exit press CTRL+C");
        DeliverCallback deliverCallback = ((consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("[*] Received '" + message +"'" );
        });
        channel.basicConsume(queueName,true, deliverCallback,consumerTag ->  {});
    }


}
