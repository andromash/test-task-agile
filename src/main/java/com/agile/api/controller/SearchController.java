package com.agile.api.controller;

import com.agile.api.entity.PictureDetails;
import com.agile.api.service.PictureDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final PictureDetailsService pictureDetailsService;

    @Autowired
    public SearchController(PictureDetailsService pictureDetailsService) {
        this.pictureDetailsService = pictureDetailsService;
    }

    @GetMapping("/${searchTerm}")
    public List<PictureDetails> searchPhotoByParameter(@PathVariable String searchTerm) {
        return pictureDetailsService.findByParameter(searchTerm);
    }
}
