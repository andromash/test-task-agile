package com.agile.api.dto;

public class PictureDetailsDto {
    private String author;
    private String camera;
    private String tags;
    private String fullPicture;
    private PictureDto pictureDto;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getFullPicture() {
        return fullPicture;
    }

    public void setFullPicture(String fullPicture) {
        this.fullPicture = fullPicture;
    }

    public PictureDto getPictureDto() {
        return pictureDto;
    }

    public void setPictureDto(PictureDto pictureDto) {
        this.pictureDto = pictureDto;
    }
}
