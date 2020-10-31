package com.agile.api.service;

import com.agile.api.dao.PictureDetailsDao;
import com.agile.api.entity.PictureDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureDetailsServiceImpl implements PictureDetailsService {
    private final PictureDetailsDao pictureDetailsDao;

    @Autowired
    public PictureDetailsServiceImpl(PictureDetailsDao pictureDetailsDao) {
        this.pictureDetailsDao = pictureDetailsDao;
    }

    @Override
    public PictureDetails add(PictureDetails details) {
        return pictureDetailsDao.add(details);
    }
}
