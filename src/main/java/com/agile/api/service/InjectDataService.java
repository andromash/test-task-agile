package com.agile.api.service;

import com.agile.api.dto.PageDto;
import com.agile.api.dto.PictureDetailsDto;
import com.agile.api.entity.Picture;
import com.agile.api.entity.PictureDetails;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InjectDataService {
    private final ApiService apiService;
    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final PictureMapper pictureMapper;
    private final PictureService pictureService;
    private final PictureDetailsService pictureDetailsService;
    private final PictureDetailsMapper pictureDetailsMapper;

    @Autowired
    public InjectDataService(ApiService apiService, CloseableHttpClient httpClient,
                             ObjectMapper objectMapper, PictureMapper pictureMapper,
                             PictureService pictureService, PictureDetailsService pictureDetailsService, PictureDetailsMapper pictureDetailsMapper) {
        this.apiService = apiService;
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.pictureMapper = pictureMapper;
        this.pictureService = pictureService;
        this.pictureDetailsService = pictureDetailsService;
        this.pictureDetailsMapper = pictureDetailsMapper;
    }

    public void injectImages() {
        try {
            pictureService.clearData();
        } catch (Exception ignored) {
        }
        String token = apiService.getAuthToken().getToken();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        int counter = 1;
        while (true) {
            String url = "http://interview.agileengine.com:80/images?page=" + counter;
            HttpGet request = new HttpGet(url);
            request.addHeader("Authorization", "Bearer " + token);
            PageDto pageDto = null;
            try (CloseableHttpResponse response = httpClient.execute(request)){
                pageDto = objectMapper.readValue(response.getEntity().getContent(), PageDto.class);
                List<Picture> pictures = pictureMapper.mapPageDtoToPictures(pageDto);
                for (int i = 0; i < pictures.size(); i++) {
                    pictureService.add(pictures.get(i));
                }
            } catch (IOException e) {
                throw new RuntimeException("Could not inject images", e);
            }
            List<Picture> pictureList = pictureService.getAll();
            for (int i = 0; i < pictureList.size(); i++) {
                String link = "http://interview.agileengine.com:80/images/" + pictureList.get(i).getId();
                HttpGet getRequest = new HttpGet(link);
                getRequest.addHeader("Authorization", "Bearer " + token);
                try (CloseableHttpResponse response = httpClient.execute(getRequest)) {
                    PictureDetailsDto detailsDto = objectMapper.readValue(response.getEntity()
                            .getContent(), PictureDetailsDto.class);
                    PictureDetails details = pictureDetailsMapper.mapDtoToDetails(detailsDto);
                    details.setPicture(pictureList.get(i).getId());
                    pictureDetailsService.add(details);
                } catch (IOException e) {
                    throw new RuntimeException("Could not inject details", e);
                }
            }
            if (!pageDto.getHasMore()) {
                break;
            }
            counter++;
        }
    }
}
