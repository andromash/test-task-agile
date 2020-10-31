package com.agile.api.controller;

import com.agile.api.service.ApiService;
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

    @Autowired
    public ImagesController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/test")
    public String getImages() {
        String token = apiService.getAuthToken().getToken();
        return token;
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
