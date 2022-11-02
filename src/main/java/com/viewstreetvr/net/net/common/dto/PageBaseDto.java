package com.viewstreetvr.net.net.common.dto;

import com.viewstreetvr.net.net.BaseDto;

public class PageBaseDto extends BaseDto {
    private int pageIndex;
    private int pageSize = 20;

    public int getPageIndex() {
        return pageIndex;
    }

    public PageBaseDto setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageBaseDto setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
