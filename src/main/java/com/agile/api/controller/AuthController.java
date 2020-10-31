package com.agile.api.controller;

import com.agile.api.dto.AuthRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping("/auth")
    public String authorize(@RequestBody AuthRequestDto authRequestDto) {

    }
}
