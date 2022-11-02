package com.viewstreetvr.net.net.event;

public class SendSMSEvent {
    private boolean success;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public SendSMSEvent setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public SendSMSEvent setSuccess(boolean success) {
        this.success = success;
        return this;
    }
}
