package com.viewstreetvr.net.net.common.dto;


public class ProvinceListDto extends PageBaseDto {
    private long countryId;

    public long getCountryId() {
        return countryId;
    }

    public ProvinceListDto setCountryId(long countryId) {
        this.countryId = countryId;
        return this;
    }
}
