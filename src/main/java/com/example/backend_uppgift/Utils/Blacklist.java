package com.example.backend_uppgift.Utils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.lang.String.valueOf;

@Component
public class Blacklist {
    public final StreamProvider streamProvider;

    public Blacklist(StreamProvider streamProvider) {
        this.streamProvider = streamProvider;
    }

    public boolean isOk(String email) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(streamProvider.getDataStreamBlacklistCheck()))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        if (response.body().contains(":true")){
            return true;
        }
        return false;
    }

    private void setAsBad(String email, String name) throws IOException, InterruptedException {
        addOrUpdate(email, name, false);
    }

    private void setAsOk(String email, String name) throws IOException, InterruptedException {
        addOrUpdate(email, name, true);
    }

    private void addOrUpdate(String email, String name, boolean ok) throws IOException, InterruptedException {
        if(upsert(email, name, ok)) return;
        add(email, name, ok);
    }

    public boolean upsert(String email, String name, boolean ok) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(streamProvider.getDataStreamBlacklist() + "/" + email))
                .header("Content-type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("{\"name\":\"" + name + "\", \"ok\":" + ok + "}"))
                .build();

        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404) {
            return add(email, name, ok);
        }

        return response.statusCode() < 300;
    }

    public boolean add(String email, String name, boolean ok) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(streamProvider.getDataStreamBlacklist()))
                .header("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"name\":\"" + name + "\", \"email\":\"" + email + "\", \"ok\":" + ok + "}"))
                .build();

        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        return response.statusCode() < 300;
    }

}