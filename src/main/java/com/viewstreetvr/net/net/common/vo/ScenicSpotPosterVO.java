package com.viewstreetvr.net.net.common.vo;

import java.io.Serializable;

public class ScenicSpotPosterVO implements Serializable {
    private long id;
    private long scenicSpotId;
    private String url;

    public long getId() {
        return id;
    }

    public ScenicSpotPosterVO setId(long id) {
        this.id = id;
        return this;
    }

    public long getScenicSpotId() {
        return scenicSpotId;
    }

    public ScenicSpotPosterVO setScenicSpotId(long scenicSpotId) {
        this.scenicSpotId = scenicSpotId;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ScenicSpotPosterVO setUrl(String url) {
        this.url = url;
        return this;
    }
}
