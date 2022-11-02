package com.viewstreetvr.net.net.event;

public class ResetLoginEvent {
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public ResetLoginEvent setSuccess(boolean success) {
        this.success = success;
        return this;
    }
}
