package com.agile.api.service;

import com.agile.api.dto.PageDto;
import com.agile.api.dto.PictureDto;
import com.agile.api.dto.PictureResponseDto;
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
            picture.setId(pageDto.getPictures().get(i).getId());
            picture.setLink(pageDto.getPictures().get(i).getCroppedPicture());
            picture.setPage(pageDto.getPage());
            pictures.add(picture);
        }
        return pictures;
    }

    public PageDto mapPicturesToDto(List<Picture> pictures) {
        PageDto pageDto = new PageDto();
        pageDto.setPage(pictures.get(0).getPage());
        List<PictureDto> pictureDtos = new ArrayList<>();
        for (int i = 0; i < pictures.size(); i++) {
            PictureDto pictureDto = new PictureDto();
            Picture picture = pictures.get(i);
            pictureDto.setCroppedPicture(pictures.get(i).getLink());
            pictureDto.setId(picture.getId());
            pictureDtos.add(pictureDto);
        }
        pageDto.setPictures(pictureDtos);
        return pageDto;
    }

    public PictureResponseDto mapPictureToResponseDto(Picture picture) {
        PictureResponseDto pictureResponseDto = new PictureResponseDto();
        pictureResponseDto.setId(picture.getId());
        pictureResponseDto.setLink(picture.getLink());
        return pictureResponseDto;
    }

    public PictureDto mapPictureToPictureDto(Picture picture) {
        PictureDto pictureDto = new PictureDto();
        pictureDto.setCroppedPicture(picture.getLink());
        pictureDto.setId(picture.getId());
        return pictureDto;
    }
}
