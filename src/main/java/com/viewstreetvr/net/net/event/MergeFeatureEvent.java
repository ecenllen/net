package com.viewstreetvr.net.net.event;

public class MergeFeatureEvent {
    private boolean success;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public MergeFeatureEvent setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public MergeFeatureEvent setSuccess(boolean success) {
        this.success = success;
        return this;
    }
}
