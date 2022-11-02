package com.viewstreetvr.net.net.constants;

public enum FeatureEnum {
    BEIDOU("北斗会员");

    private String desc;

    FeatureEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public FeatureEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
