package com.viewstreetvr.net.net.common.dto;

public class ProvinceScenicSpotsDto extends PageBaseDto {
    private long provinceId;

    public long getProvinceId() {
        return provinceId;
    }

    public ProvinceScenicSpotsDto setProvinceId(long provinceId) {
        this.provinceId = provinceId;
        return this;
    }
}
