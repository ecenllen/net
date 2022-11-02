package com.viewstreetvr.net.net.common.vo;


import java.util.List;

public class ImportScenicSpotVO {
    private String path;
    private List<ScenicSpotVO> data;

    public String getPath() {
        return path;
    }

    public ImportScenicSpotVO setPath(String path) {
        this.path = path;
        return this;
    }

    public List<ScenicSpotVO> getData() {
        return data;
    }

    public ImportScenicSpotVO setData(List<ScenicSpotVO> data) {
        this.data = data;
        return this;
    }
}
