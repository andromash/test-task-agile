package com.agile.api.controller;

import com.agile.api.dao.PictureDao;
import com.agile.api.entity.Picture;
import com.agile.api.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final PictureService pictureService;

    @Autowired
    public SearchController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/${searchTerm}")
    public List<Picture> searchPhotoByParameter(@PathVariable String searchTerm) {
        Map<String, String[]> map = new HashMap<>();
        String[] terms = searchTerm.split("&");
        for (int i = 0; i < terms.length; i++) {
            String[] split = terms[i].split("=");
            map.put(split[0], new String[]{split[1]});
        }
        return pictureService.getByParameter(map);
    }
}
