package com.viewstreetvr.net.net;

import android.app.Application;

public class NetApplication extends Application {
    public static NetApplication appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        UtilInitial.init(this);

    }
}
