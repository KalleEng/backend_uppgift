package com.example.backend_uppgift.Utils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class Blacklist {
    public final StreamProvider streamProvider;

    public Blacklist(StreamProvider streamProvider) {
        this.streamProvider = streamProvider;
    }

    public boolean isOk(String email) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(streamProvider.getDataStreamBlacklist()
                + email))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        if (response.body().contains(":true")){
            return true;
        }
        return false;
    }
}
