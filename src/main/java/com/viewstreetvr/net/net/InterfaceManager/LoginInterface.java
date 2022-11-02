package com.viewstreetvr.net.net.InterfaceManager;


import com.viewstreetvr.net.net.ApiResponse;
import com.viewstreetvr.net.net.AppExecutors;
import com.viewstreetvr.net.net.BaseDto;
import com.viewstreetvr.net.net.CacheUtils;
import com.viewstreetvr.net.net.DataResponse;
import com.viewstreetvr.net.net.HttpUtils;
import com.viewstreetvr.net.net.common.CommonApiService;
import com.viewstreetvr.net.net.common.dto.DeleteUserBySelfDto;
import com.viewstreetvr.net.net.common.dto.MergeFeatureFromOrderDto;
import com.viewstreetvr.net.net.common.dto.RegisterUserDto;
import com.viewstreetvr.net.net.common.vo.LoginVO;
import com.viewstreetvr.net.net.common.vo.UserFeatureVO;
import com.viewstreetvr.net.net.constants.Constant;
import com.viewstreetvr.net.net.event.AutoLoginEvent;
import com.viewstreetvr.net.net.event.DeleteUserEvent;
import com.viewstreetvr.net.net.event.MergeFeatureEvent;
import com.viewstreetvr.net.net.event.RegisterEvent;
import com.viewstreetvr.net.net.event.RegisterLoginEvent;
import com.viewstreetvr.net.net.event.ResetLoginEvent;
import com.viewstreetvr.net.net.event.TokenEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

public class LoginInterface {

    public static void loadConfigs() {
        CacheUtils.isLoginSuccess.set(true);
        AppExecutors.runNetworkIO(() -> {
            DataResponse<Map<String, String>> response = HttpUtils.getInstance().getService(CommonApiService.class)
                    .configs(new BaseDto());
            // 900 token 过期
            if (response.success() && response.getData() != null) {
                LoginVO loginData = CacheUtils.getLoginData();
                loginData.setConfigs(response.getData());
                CacheUtils.setLoginData(loginData);
            } else if (Constant.TOKEN_CODE == response.getCode()) {
                EventBus.getDefault().post(new AutoLoginEvent());
                CacheUtils.isLoginSuccess.set(false);
            } else {
                EventBus.getDefault().post(new AutoLoginEvent());
                CacheUtils.isLoginSuccess.set(false);
            }
        });
    }

    /**
     * 注销帐号
     *
     * @param password
     */
    public static void logoutAccount(String password) {
        AppExecutors.runNetworkIO(() -> {
            ApiResponse register = HttpUtils.getInstance().getService(CommonApiService.class)
                    .deleteUserBySelf(new DeleteUserBySelfDto(password));
            // 900 token 过期
            if (register.success()) {
                CacheUtils.exitLogin();
                EventBus.getDefault().post(new DeleteUserEvent().setSuccess(register.success()));
            } else if (Constant.TOKEN_CODE == register.getCode()) {
//                EventBus.getDefault().post(new TokenEvent());
            } else {
                EventBus.getDefault().post(new DeleteUserEvent().setSuccess(register.success()).setMsg(register.getMessage()));
            }
        });
    }

    public static void login(String username, String password) {
        CacheUtils.isLoginSuccess.set(true);
        AppExecutors.runNetworkIO(() -> {
            DataResponse<LoginVO> login = HttpUtils.getInstance().getService(CommonApiService.class)
                    .login(new RegisterUserDto(username, password));

            if (login.success()) {
                CacheUtils.setLoginData(login.getData());
                CacheUtils.setUserNamePassword(username, password);
            } else {
                CacheUtils.exitLogin();
//                loadConfigs();
            }
            EventBus.getDefault().post(new AutoLoginEvent().setSuccess(login.success()).setMsg(login.getMessage()));
        });
    }

    public static void registerLogin(String username, String password) {
        CacheUtils.isLoginSuccess.set(true);
        AppExecutors.runNetworkIO(() -> {
            DataResponse<LoginVO> login = HttpUtils.getInstance().getService(CommonApiService.class)
                    .registerLogin(new RegisterUserDto(username, password));

            if (login.success()) {
                CacheUtils.setLoginData(login.getData());
                CacheUtils.setUserNamePassword(username, password);
            } else {
                CacheUtils.exitLogin();
//                loadConfigs();
            }
            EventBus.getDefault().post(new RegisterLoginEvent().setSuccess(login.success()).setMsg(login.getMessage()));

        });
    }

    public static void register(String username, String password) {
        AppExecutors.runNetworkIO(() -> {
            ApiResponse register = HttpUtils.getInstance().getService(CommonApiService.class)
                    .register(new RegisterUserDto(username, password));
            // 900 token 过期
            if (register.success()) {
                EventBus.getDefault().post(new RegisterEvent().setSucceed(register.success()));
            } else if (Constant.TOKEN_CODE == register.getCode()) {
                EventBus.getDefault().post(new TokenEvent());
            } else {
                EventBus.getDefault().post(new RegisterEvent().setSucceed(register.success()).setMsg(register.getMessage()).setCode(register.getCode()));
            }
        });
    }

    public static void addOldVip() {
        AppExecutors.runNetworkIO(new Runnable() {
            @Override
            public void run() {
                ApiResponse apiResponse = HttpUtils.getInstance().getService(CommonApiService.class).addOldVip(new BaseDto());
                if (apiResponse.success()) {
                    resetLoginDate();
                } else {

                }
            }
        });
    }

    public static void resetLoginDate() {
        AppExecutors.runNetworkIO(new Runnable() {
            @Override
            public void run() {
                DataResponse<List<UserFeatureVO>> listDataResponse = HttpUtils.getInstance().getService(CommonApiService.class)
                        .userFeatures(new BaseDto());
                if (listDataResponse.success()) {
                    LoginVO loginData = CacheUtils.getLoginData();
                    loginData.setUserFeatures(listDataResponse.getData());
                    CacheUtils.setLoginData(loginData);
                }
                EventBus.getDefault().post(new ResetLoginEvent().setSuccess(listDataResponse.success()));
            }
        });

    }

    public static void mergeFeatureFromOrder(String orderNo) {
        AppExecutors.runNetworkIO(new Runnable() {
            @Override
            public void run() {
                DataResponse<List<UserFeatureVO>> apiResponse = HttpUtils.getInstance().getService(CommonApiService.class)
                        .mergeFeatureFromOrder(new MergeFeatureFromOrderDto(orderNo));
                if (apiResponse.success()) {
                    LoginVO loginData = CacheUtils.getLoginData();
                    loginData.setUserFeatures(apiResponse.getData());
                    CacheUtils.setLoginData(loginData);
                }
                EventBus.getDefault().post(new MergeFeatureEvent().setSuccess(apiResponse.success()).setMsg(apiResponse.getMessage()));
            }
        });
    }

}
