package com.viewstreetvr.net.net;

import android.content.Context;

import com.viewstreetvr.net.net.util.SharePreferenceUtils;

/**
 * Author: liaohaiping
 * Time: 2019-06-09
 * Description:
 */
public class UtilInitial {
    public static void init(Context context){
        CacheUtils.init(context);
        SharePreferenceUtils.initSharePreference(context);
    }
}
