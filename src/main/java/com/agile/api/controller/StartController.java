package com.agile.api.controller;

import com.agile.api.service.InjectDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartController {
    private final InjectDataService injectDataService;

    @Autowired
    public StartController(InjectDataService injectDataService) {
        this.injectDataService = injectDataService;
    }

    @GetMapping
    public void injectData() {
        injectDataService.injectData();
    }
}
