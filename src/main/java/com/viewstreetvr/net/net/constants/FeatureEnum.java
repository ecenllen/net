package com.viewstreetvr.net.net.constants;

public enum FeatureEnum {
    BEIDOU("εζδΌε");

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
