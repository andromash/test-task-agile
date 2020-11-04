package com.agile.api.service.mapper;

import com.agile.api.dto.PageDto;
import com.agile.api.entity.Picture;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class PictureMapper {

    public List<Picture> mapPageDtoToPictures(PageDto pageDto) {
        List<Picture> pictures = new ArrayList<>();
        for (int i = 0; i < pageDto.getPictures().size(); i++) {
            Picture picture = new Picture();
            picture.setImageId(pageDto.getPictures().get(i).getId());
            picture.setLink(pageDto.getPictures().get(i).getCroppedPicture());
            picture.setPage(pageDto.getPage());
            pictures.add(picture);
        }
        return pictures;
    }

}
