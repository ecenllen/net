package com.viewstreetvr.net.net.common.dto;

import com.viewstreetvr.net.net.BaseDto;

public class PanoramasDto extends BaseDto {
    private String title;
    private int pageIndex;
    private int pageSize = 20;
    private boolean international;

    public PanoramasDto(String text, boolean international, int pageIndex, int pageSize) {
        this.title = text;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.international = international;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isInternational() {
        return international;
    }

    public void setInternational(boolean international) {
        this.international = international;
    }
}
