package com.agile.api.service;

import com.agile.api.entity.Picture;
import java.util.List;
import java.util.Map;

public interface PictureService {

    Picture add(Picture picture);

    List<Picture> getAll();

    List<Picture> getByParameter(Map<String, String[]> parameters);

    void clearData();
}
