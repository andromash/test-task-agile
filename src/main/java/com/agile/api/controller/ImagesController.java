package com.agile.api.controller;

import com.agile.api.dto.PageDto;
import com.agile.api.dto.PictureResponseDto;
import com.agile.api.service.PictureMapper;
import com.agile.api.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/images")
public class ImagesController {
    private final PictureService pictureService;
    private final PictureMapper pictureMapper;

    @Autowired
    public ImagesController(PictureService pictureService, PictureMapper pictureMapper) {
        this.pictureService = pictureService;
        this.pictureMapper = pictureMapper;
    }

    @GetMapping("/test")
    public PageDto getImages() {
        return pictureMapper.mapPicturesToDto(pictureService.getAll());
    }

    @GetMapping
    public PageDto getImagesFromPage(@RequestParam int page) {
        return pictureMapper.mapPicturesToDto(pictureService.getByParameter(
                Map.of("page", new String[]{String.valueOf(page)})));
    }

    @GetMapping("/${id}")
    public PictureResponseDto getPhotoDetails(@PathVariable String id) {
        return pictureMapper.mapPictureToResponseDto(
                pictureService.getByParameter(Map.of("id", new String[]{id})).get(0));
    }
}
