package com.agile.api.service.mapper;

import com.agile.api.dto.PictureDetailsDto;
import com.agile.api.entity.PictureDetails;
import org.springframework.stereotype.Component;

@Component
public class PictureDetailsMapper {

    public PictureDetails mapDtoToDetails(PictureDetailsDto dto) {
        PictureDetails details = new PictureDetails();
        details.setAuthor(dto.getAuthor());
        details.setCamera(dto.getCamera());
        details.setFullPicture(dto.getFullPicture());
        details.setTags(dto.getTags());
        return details;
    }
}
