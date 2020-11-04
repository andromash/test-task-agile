package com.agile.api.dao;

import com.agile.api.entity.PictureDetails;
import java.util.List;

public interface PictureDetailsDao {

    PictureDetails add(PictureDetails details);

    List<PictureDetails> getByDynamicParameters(String parameter);
}
