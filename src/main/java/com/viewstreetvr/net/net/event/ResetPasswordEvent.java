package com.viewstreetvr.net.net.event;

public class ResetPasswordEvent {
    private boolean succeed;

    public boolean isSucceed() {
        return succeed;
    }

    public ResetPasswordEvent setSucceed(boolean succeed) {
        this.succeed = succeed;
        return this;
    }
}
