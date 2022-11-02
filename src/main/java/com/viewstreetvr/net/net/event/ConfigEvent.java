package com.viewstreetvr.net.net.event;

public class ConfigEvent {
    private boolean success;
    private String msg;
    private boolean isNeedVerificationCode;

    public boolean isNeedVerificationCode() {
        return isNeedVerificationCode;
    }

    public ConfigEvent setNeedVerificationCode(boolean needVerificationCode) {
        isNeedVerificationCode = needVerificationCode;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public ConfigEvent setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ConfigEvent setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
