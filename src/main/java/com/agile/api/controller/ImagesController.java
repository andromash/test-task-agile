package com.agile.api.controller;

import com.agile.api.dto.PageDto;
import com.agile.api.entity.Picture;
import com.agile.api.service.ApiService;
import com.agile.api.service.InjectDataService;
import com.agile.api.service.PictureMapper;
import com.agile.api.service.PictureService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
public class ImagesController {
    private final ApiService apiService;
    private final ObjectMapper mapper;
    private final CloseableHttpClient httpClient;
    private final PictureService pictureService;
    private final PictureMapper pictureMapper;

    @Autowired
    public ImagesController(ApiService apiService, ObjectMapper mapper,
                            CloseableHttpClient httpClient,
                            PictureService pictureService, PictureMapper pictureMapper) {
        this.apiService = apiService;
        this.mapper = mapper;
        this.httpClient = httpClient;
        this.pictureService = pictureService;
        this.pictureMapper = pictureMapper;
    }

    @GetMapping("/test")
    public PageDto getImages() {
        return pictureMapper.mapPicturesToDto(pictureService.getAll());
    }

    @GetMapping
    public String getImagesFromPage(@RequestParam int page) {
        return "";
    }

    @GetMapping("/${id}")
    public String getPhotoDetails(@PathVariable Long id) {
        return "";
    }
}
