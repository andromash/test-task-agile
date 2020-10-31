package com.agile.api.service;

import com.agile.api.dto.AuthResponseDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Service
public class ApiService {
    private final CloseableHttpClient httpClient;
    private final ObjectMapper mapper;

    @Autowired
    public ApiService(ObjectMapper mapper, CloseableHttpClient httpClient) {
        this.mapper = mapper;
        this.httpClient = httpClient;
    }

    public AuthResponseDto getAuthToken() {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String url = "http://interview.agileengine.com/auth";
        HttpPost request = new HttpPost(url);
        try {
            request.setEntity(new StringEntity("{\"apiKey\" : \"23567b218376f79d9415\"}"));
            request.addHeader("Content-Type", "application/json");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Could not connect to URL " + url, e);
        }
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return mapper.readValue(response.getEntity().getContent(), AuthResponseDto.class);
        } catch (IOException e) {
            throw new RuntimeException("Can not send POST request to " + url, e);
        }

    }
}
