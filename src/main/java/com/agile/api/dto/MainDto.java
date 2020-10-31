package com.agile.api.dto;

import java.util.List;

public class MainDto {
    private InfoDto infoDto;
    private List<StepDto> stepDto;

    public InfoDto getInfoDto() {
        return infoDto;
    }

    public void setInfoDto(InfoDto infoDto) {
        this.infoDto = infoDto;
    }

    public List<StepDto> getStepDto() {
        return stepDto;
    }

    public void setStepDto(List<StepDto> stepDto) {
        this.stepDto = stepDto;
    }
}
