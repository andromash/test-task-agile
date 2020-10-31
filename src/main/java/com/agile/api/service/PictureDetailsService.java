package com.agile.api.service;

import com.agile.api.entity.PictureDetails;

import java.util.List;

public interface PictureDetailsService {

    PictureDetails add(PictureDetails details);

    List<PictureDetails> findByParameter(String parameter);
}
