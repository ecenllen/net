package com.viewstreetvr.net.net.event;

/**
 * Created by yingyongduoduo on 2019/7/1.
 */

public class RegisterLoginEvent {
    private boolean success;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public RegisterLoginEvent setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public RegisterLoginEvent setSuccess(boolean success) {
        this.success = success;
        return this;
    }
}
