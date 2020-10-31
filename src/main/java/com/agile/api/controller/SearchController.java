package com.agile.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

    @GetMapping("/${searchTerm}")
    public String searchPhotoByParameter(@PathVariable String searchTerm) {
        return "";
    }
}
