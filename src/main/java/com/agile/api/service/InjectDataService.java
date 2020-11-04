package com.agile.api.service;

import com.agile.api.dto.PageDto;
import com.agile.api.dto.PictureDetailsDto;
import com.agile.api.entity.Picture;
import com.agile.api.entity.PictureDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InjectDataService {
    private static final String URL = "http://interview.agileengine.com:80/images";
    private final ApiService apiService;
    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final PictureMapper pictureMapper;
    private final PictureService pictureService;
    private final PictureDetailsService pictureDetailsService;
    private final PictureDetailsMapper pictureDetailsMapper;

    @Autowired
    public InjectDataService(ApiService apiService, PictureDetailsService pictureDetailsService,
                             ObjectMapper objectMapper, CloseableHttpClient httpClient,
                             PictureService pictureService, PictureMapper pictureMapper,
                             PictureDetailsMapper pictureDetailsMapper) {
        this.apiService = apiService;
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.pictureMapper = pictureMapper;
        this.pictureService = pictureService;
        this.pictureDetailsService = pictureDetailsService;
        this.pictureDetailsMapper = pictureDetailsMapper;
    }

    public void injectData() {
        try {
            pictureService.clearData();
        } catch (Exception ignored) {
        }
        String token = apiService.getAuthToken().getToken();
        int counter = 1;
        while (injectImages("?page=" + counter, token)) {
            counter++;
        }
        injectImageDetails(pictureService.getAll(), token);
    }

    private boolean injectImages(String url, String token) {
        HttpGet request = new HttpGet(URL + url);
        request.addHeader("Authorization", "Bearer " + token);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            PageDto pageDto = objectMapper.readValue(response.getEntity().getContent(),
                    PageDto.class);
            List<Picture> pictures = pictureMapper.mapPageDtoToPictures(pageDto);
            for (int i = 0; i < pictures.size(); i++) {
                pictureService.add(pictures.get(i));
            }
            return pageDto.getHasMore();
        } catch (IOException e) {
            throw new RuntimeException("Could not inject images", e);
        }
    }

    private void injectImageDetails(List<Picture> pictureList, String token) {
        for (Picture picture : pictureList) {
            HttpGet getRequest = new HttpGet(URL + "/"
                    + picture.getId());
            getRequest.addHeader("Authorization", "Bearer " + token);
            try (CloseableHttpResponse response = httpClient.execute(getRequest)) {
                PictureDetailsDto detailsDto = objectMapper.readValue(response.getEntity()
                        .getContent(), PictureDetailsDto.class);
                PictureDetails details = pictureDetailsMapper.mapDtoToDetails(detailsDto);
                details.setPicture(picture.getId());
                pictureDetailsService.add(details);
            } catch (IOException e) {
                throw new RuntimeException("Could not inject details", e);
            }
        }
    }
}
