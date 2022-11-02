package com.viewstreetvr.net.net;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.viewstreetvr.net.BuildConfig;
import com.viewstreetvr.net.net.common.vo.LoginVO;
import com.viewstreetvr.net.net.common.vo.UserFeatureVO;
import com.viewstreetvr.net.net.common.vo.UserPassword;
import com.viewstreetvr.net.net.constants.Constant;
import com.viewstreetvr.net.net.constants.FeatureEnum;
import com.viewstreetvr.net.net.constants.SysConfigEnum;
import com.viewstreetvr.net.net.util.GsonUtil;
import com.viewstreetvr.net.net.util.SharePreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class CacheUtils {
    private static String SHARED_PREFERENCE_KEY;
    private static Context context;
    public static AtomicBoolean isLoginSuccess = new AtomicBoolean(false);

    public static void init(Context ctx) {
        context = ctx;
        SHARED_PREFERENCE_KEY = ctx.getPackageName();
    }

    public static boolean isNeedPay() {
        if (BuildConfig.DEBUG)
            return false;
        return CacheUtils.getLoginData().getConfigBoolean("ischarge", false);
    }

    public static boolean isNeedLocPermission() {
        return !(boolean) SharePreferenceUtils.get(Constant.LOCATION_PERMISSION, false)
                && Build.VERSION.SDK_INT <= Build.VERSION_CODES.O && (Build.BRAND.equalsIgnoreCase("VIVO")
                || Build.BRAND.equalsIgnoreCase("OPPO"));
    }

    private static SharedPreferences getSharedPreferences(String key) {
        return context.getSharedPreferences(key, Context.MODE_PRIVATE);
    }

    private static SharedPreferences getPublicPreferences() {
        return getSharedPreferences(SHARED_PREFERENCE_KEY);
    }

    public static void setConfigs(Map<String, String> configs) {
        SharedPreferences.Editor edit = getPublicPreferences().edit();
        for (String key : configs.keySet()) {
            String value = configs.get(key);
            edit.putString(key, value);
        }
        edit.apply();
    }

    public static void setLoginData(LoginVO loginData) {
        String json = GsonUtil.toJson(loginData);

        isLoginSuccess.set(!TextUtils.isEmpty(json));

        getPublicPreferences().edit()
                .putString("loginData", json)
                .putString("loginData.token", loginData.getToken())
                .apply();

    }

    public static void setUserNamePassword(String userName, String password) {
        getPublicPreferences().edit()
                .putString("login.userName", userName)
                .putString("login.password", password)
                .apply();
    }

    public static UserPassword getUserPassword() {
        SharedPreferences publicPreferences = getPublicPreferences();
        if (publicPreferences.contains("login.userName")) {
            String userName = publicPreferences.getString("login.userName", "");
            String password = publicPreferences.getString("login.password", "");
            return new UserPassword(userName, password);
        }
        return new UserPassword();

    }

    public static LoginVO getLoginData() {
        SharedPreferences preferences = getPublicPreferences();
        if (!preferences.contains("loginData")) {
            return new LoginVO();
        }
        String json = preferences.getString("loginData", "");
        if (TextUtils.isEmpty(json)) {
            return new LoginVO();
        }
        try {
            return GsonUtil.fromJson(json, new TypeToken<LoginVO>() {
            }.getType());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LoginVO();

    }

    public static boolean isLogin() {
//        return !TextUtils.isEmpty(CacheUtils.getUserPassword().getUserName());
        String token = getToken();
        if (TextUtils.isEmpty(token)) {
            return false;
        }
        String[] arr = token.split("\\.");
        if (arr.length != 3) {
            return false;
        }
        byte[] bytes = Base64.decode(arr[1], Base64.URL_SAFE);
        //        {"jti":"lhp","sub":"TEXT2VOICE","iss":"com.xbq.webapi","iat":1560650388,"exp":1560657588}
        String json = Charset.forName("utf-8").decode(ByteBuffer.wrap(bytes)).toString();
        try {
            Log.d("lhp", "token payload: " + json);
            JSONObject jsonObject = new JSONObject(json);
            long expireTime = jsonObject.optLong("exp", TimeUtils.getTimeAfterNow(1, TimeUnitEnum.DAY).getTime());
            return expireTime * 1000 > System.currentTimeMillis();

        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void exitLogin() {
        CacheUtils.setUserNamePassword("", "");
        LoginVO loginData = CacheUtils.getLoginData();
        if (loginData.getConfigs().size() == 0 && TextUtils.isEmpty(loginData.getUserName()))
            CacheUtils.isLoginSuccess.set(false);
        loginData.setUserName("");
        loginData.setUserId("");
        loginData.setToken("");
        loginData.setUserFeatures(null);
        setLoginData(loginData);
    }

    public static String getToken() {
        return getPublicPreferences().getString("loginData.token", "");
    }

    public static boolean canUse(FeatureEnum featureEnum) {
        LoginVO loginData = getLoginData();
        UserFeatureVO feature = Linq.of(loginData.getUserFeatures()).first(f -> f.getFeature().equals(featureEnum));
        if (feature != null && feature.isValid()) {
            return true;
        }
        return false;
    }

    public static String getConfig(String configName, String defaultValue) {
        return getLoginData().getConfig(configName, defaultValue);
//        return getPublicPreferences().getString(configName, defaultValue);
    }


    public static String getConfig(SysConfigEnum configEnum) {
        return getLoginData().getConfig(configEnum.getKeyName(), configEnum.getValue());
//        return getPublicPreferences().getString(configEnum.getKeyName(), configEnum.getValue());
    }


    public static int getFreeCount(String configName, int defaultValue) {
        return getSharedPreferences("freeCount").getInt(configName, defaultValue);
    }

    public static void setFreeCountConfigInt(String configName, int defaultValue) {
        getSharedPreferences("freeCount").edit().putInt(configName, defaultValue).apply();
    }

    public static int getConfigInt(SysConfigEnum configEnum) {
        return getConfigInt(configEnum.getKeyName(), configEnum.getValueInt());
    }

    public static int getConfigInt(String configName, int defaultValue) {
        String config = getConfig(configName, String.valueOf(defaultValue));
        try {
            return Integer.parseInt(config);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static boolean getConfigBoolean(String configName, boolean defaultValue) {
        String config = getConfig(configName, String.valueOf(defaultValue));
        try {
            return Boolean.valueOf(config);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static boolean getConfigBoolean(SysConfigEnum configEnum) {
        return getConfigBoolean(configEnum.getKeyName(), configEnum.getValueBoolean());
    }

    public static String getADConfig(String configName, String defaultValue) {
        return getPublicPreferences().getString(configName, defaultValue);
    }

    public static String getADConfig(SysConfigEnum configEnum) {
        return getPublicPreferences().getString(configEnum.getKeyName(), configEnum.getValue());
    }

    public static void save(String key, String value) {
        getPublicPreferences()
                .edit()
                .putString(key, value)
                .apply();
    }

    public static boolean isCanRequestPermission() {
        long requestTime = getPublicPreferences().getLong("isRequestPermission", 0L);
        if (System.currentTimeMillis() - requestTime < 1000 * 60 * 60 * 48) {
            return false;
        }
        getPublicPreferences().edit().putLong("isRequestPermission", System.currentTimeMillis()).apply();
        return true;
    }

}
