package com.agile.api.service;

import com.agile.api.dao.PictureDao;
import com.agile.api.dto.PageDto;
import com.agile.api.entity.Picture;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
    private final ApiService apiService;
    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final PictureMapper pictureMapper;
    private final PictureService pictureService;

    @Autowired
    public InjectDataService(ApiService apiService, CloseableHttpClient httpClient,
                             ObjectMapper objectMapper, PictureMapper pictureMapper,
                             PictureService pictureService) {
        this.apiService = apiService;
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.pictureMapper = pictureMapper;
        this.pictureService = pictureService;
    }

    public void injectData() {
        pictureService.clearData();
        String token = apiService.getAuthToken().getToken();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String url = "http://interview.agileengine.com:80/images";
        HttpGet request = new HttpGet(url);
        request.addHeader("Authorization", "Bearer " + token);

        try (CloseableHttpResponse response = httpClient.execute(request)){
            PageDto pageDto = objectMapper.readValue(response.getEntity().getContent(), PageDto.class);
            List<Picture> pictures = pictureMapper.mapPageDtoToPictures(pageDto);
            for (int i = 0; i < pictures.size(); i++) {
                pictureService.add(pictures.get(i));
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not inject images", e);
        }
    }
}
