package com.viewstreetvr.net.net.common.vo;

import com.viewstreetvr.net.net.NetApplication;
import com.viewstreetvr.net.net.constants.SysConfigEnum;
import com.viewstreetvr.net.net.util.PublicUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginVO {
    private String token;
    private String userName;
    private String userId;
    private List<UserFeatureVO> userFeatures;
    private Map<String, String> configs = new HashMap<>();

    public String getToken() {
        return token;
    }

    public String getUserId() {
        return userId;
    }

    public LoginVO setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<UserFeatureVO> getUserFeatures() {
        return userFeatures;
    }

    public void setUserFeatures(List<UserFeatureVO> userFeatures) {
        this.userFeatures = userFeatures;
    }

    public Map<String, String> getConfigs() {
        return configs;
    }

    public LoginVO setConfigs(Map<String, String> configs) {
        this.configs = configs;
        return this;
    }

    public int getConfigInt(String configName, int defaultValue) {
        if (configs.containsKey(configName)) {
            return Integer.valueOf(configs.get(configName));
        } else {
            return defaultValue;
        }
    }

    public void setConfigInt(String configName, int defaultValue) {
        configs.put(configName, String.valueOf(defaultValue));
    }

    public long getConfigLong(String configName, long defaultValue) {
        if (configs.containsKey(configName)) {
            return Long.valueOf(configs.get(configName));
        } else {
            return defaultValue;
        }
    }

    public String getConfig(SysConfigEnum configEnum) {
        return getConfig(configEnum.getKeyName(), configEnum.getValue());
    }

    public String getConfig(String configName, String defaultValue) {
        if (configs.containsKey(configName)) {
            return configs.get(configName);
        } else {
            return defaultValue;
        }
    }

    public String getDownloadUrl() {
        return getConfig("app_download_url", PublicUtil.getAppName(NetApplication.appContext));
    }


    public boolean getConfigBoolean(String configName, boolean defaultValue) {
        if (configs.containsKey(configName)) {
            return Boolean.parseBoolean(configs.get(configName));
        } else {
            return defaultValue;
        }
    }

    public double getConfigDouble(String configName, double defaultValue) {
        if (configs.containsKey(configName)) {
            return Long.valueOf(configs.get(configName));
        } else {
            return defaultValue;
        }
    }
}
