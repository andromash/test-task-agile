package com.agile.api.dao;

import com.agile.api.entity.Picture;

import java.util.List;
import java.util.Map;

public interface PictureDao {

    Picture add(Picture picture);

    List<Picture> getByParameter(Map<String, String[]> parameters);

    void clearData();
}
