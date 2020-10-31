package com.agile.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PictureDto {
    private String id;
    @JsonProperty("cropped_picture")
    private String croppedPicture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCroppedPicture() {
        return croppedPicture;
    }

    public void setCroppedPicture(String croppedPicture) {
        this.croppedPicture = croppedPicture;
    }
}
