package com.agile.api.dto;

import java.util.List;

public class PageDto {
    private List<PictureDto> pictures;
    private Integer page;
    private Long pageCount;
    private Boolean hasMore;

    public List<PictureDto> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureDto> pictures) {
        this.pictures = pictures;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
