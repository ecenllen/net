package com.viewstreetvr.net.net.common.dto;


public class SearchScenicSpotDto extends PageBaseDto {
    private String keyword;
    private String tag;
    private long countryId;
    private long provinceId;
    private Boolean international;
    private Boolean userUpload;

    public SearchScenicSpotDto(){};
    public SearchScenicSpotDto(String text, String tag, boolean international, int currentPage, int totalSize) {
        this.keyword = text;
        this.tag = tag;
        this.international = international;
        setPageIndex(currentPage);
        setPageSize(totalSize);
    }


    public String getKeyword() {
        return keyword;
    }

    public SearchScenicSpotDto setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public SearchScenicSpotDto setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public long getCountryId() {
        return countryId;
    }

    public SearchScenicSpotDto setCountryId(long countryId) {
        this.countryId = countryId;
        return this;
    }

    public long getProvinceId() {
        return provinceId;
    }

    public SearchScenicSpotDto setProvinceId(long provinceId) {
        this.provinceId = provinceId;
        return this;
    }

    public Boolean getInternational() {
        return international;
    }

    public SearchScenicSpotDto setInternational(Boolean international) {
        this.international = international;
        return this;
    }

    public Boolean getUserUpload() {
        return userUpload;
    }

    public SearchScenicSpotDto setUserUpload(Boolean userUpload) {
        this.userUpload = userUpload;
        return this;
    }
}
