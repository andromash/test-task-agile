package com.agile.api.service;

import com.agile.api.dao.PictureDao;
import com.agile.api.entity.Picture;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureDao pictureDao;

    @Autowired
    public PictureServiceImpl(PictureDao pictureDao) {
        this.pictureDao = pictureDao;
    }

    @Override
    public Picture add(Picture picture) {
        return pictureDao.add(picture);
    }

    @Override
    public List<Picture> getAll() {
        Map<String, String[]> defaultMap = new HashMap<>();
        defaultMap.put("page", new String[]{"1"});
        return pictureDao.getByParameter(defaultMap);
    }

    @Override
    public List<Picture> getByParameter(Map<String, String[]> parameters) {
        return pictureDao.getByParameter(parameters);
    }

    @Override
    public void clearData() {
        pictureDao.clearData();
    }
}
