package com.viewstreetvr.net.net.event;

/**
 * Created by yingyongduoduo on 2019/6/29.
 */

public class DeleteUserEvent {
    private boolean success;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public DeleteUserEvent setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public DeleteUserEvent setSuccess(boolean success) {
        this.success = success;
        return this;
    }
}
