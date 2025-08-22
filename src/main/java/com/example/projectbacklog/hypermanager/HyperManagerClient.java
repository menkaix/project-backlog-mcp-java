package com.example.projectbacklog.hypermanager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HyperManagerClient {

    private final OkHttpClient client = new OkHttpClient();
    private final HyperManagerProperties properties;

    public HyperManagerClient(HyperManagerProperties properties) {
        this.properties = properties;
    }

    public String get(String path) throws IOException {
        Request request = new Request.Builder()
                .url(properties.getBaseUrl() + path)
                .addHeader("x-api-key", properties.getApiKey())
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    public String post(String path, String json) throws IOException {
        Request request = new Request.Builder()
                .url(properties.getBaseUrl() + path)
                .addHeader("x-api-key", properties.getApiKey())
                .post(okhttp3.RequestBody.create(json, okhttp3.MediaType.parse("application/json; charset=utf-8")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    public String patch(String path, String json) throws IOException {
        Request request = new Request.Builder()
                .url(properties.getBaseUrl() + path)
                .addHeader("x-api-key", properties.getApiKey())
                .patch(okhttp3.RequestBody.create(json, okhttp3.MediaType.parse("application/json; charset=utf-8")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }
}