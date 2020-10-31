package com.agile.api.service;

import com.agile.api.dto.PictureDetailsDto;
import com.agile.api.entity.PictureDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PictureDetailsMapper {
    private final PictureMapper mapper;

    @Autowired
    public PictureDetailsMapper(PictureMapper mapper) {
        this.mapper = mapper;
    }

    public PictureDetailsDto mapDetailsToDto(PictureDetails details) {
        PictureDetailsDto dto = new PictureDetailsDto();
        dto.setAuthor(details.getAuthor());
        dto.setCamera(details.getCamera());
        dto.setFullPicture(details.getFullPicture());
        dto.setTags(details.getTags());
        dto.setPictureDto(mapper.mapPictureToPictureDto(details.getPicture()));
        return dto;
    }

    public PictureDetails mapDtoToDetails(PictureDetailsDto dto) {
        PictureDetails details = new PictureDetails();
        details.setAuthor(dto.getAuthor());
        details.setCamera(dto.getCamera());
        details.setFullPicture(dto.getFullPicture());
        details.setTags(dto.getTags());
        return details;
    }
}
