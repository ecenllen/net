package com.viewstreetvr.net.net.InterfaceManager;


import com.viewstreetvr.net.net.ApiResponse;
import com.viewstreetvr.net.net.AppExecutors;
import com.viewstreetvr.net.net.HttpUtils;
import com.viewstreetvr.net.net.common.CommonApiService;
import com.viewstreetvr.net.net.common.dto.ChangePasswordDto;
import com.viewstreetvr.net.net.common.dto.RegisterBySmsCodeDto;
import com.viewstreetvr.net.net.common.dto.RegisterUserDto;
import com.viewstreetvr.net.net.common.dto.SendSmsCodeDto;
import com.viewstreetvr.net.net.constants.Constant;
import com.viewstreetvr.net.net.event.RegisterEvent;
import com.viewstreetvr.net.net.event.RegisterLoginEvent;
import com.viewstreetvr.net.net.event.ResetPasswordEvent;
import com.viewstreetvr.net.net.event.SendSMSEvent;
import com.viewstreetvr.net.net.event.TokenEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yingyongduoduo on 2019/6/24.
 */

public class RegisterInterface {

    public static void loadRegister(String username, String password) {
        AppExecutors.runNetworkIO(() -> {
            ApiResponse register = HttpUtils.getInstance().getService(CommonApiService.class)
                    .register(new RegisterUserDto(username, password));
            // 900 token 过期
            if (register.success()) {
                EventBus.getDefault().post(new RegisterEvent().setSucceed(register.success()));
            } else if (Constant.TOKEN_CODE == register.getCode()) {
//                EventBus.getDefault().post(new TokenEvent());
            } else {
                EventBus.getDefault().post(new RegisterEvent().setSucceed(register.success()).setMsg(register.getMessage()).setCode(register.getCode()));
            }
        });
    }

    public static void registerBySmsCode(String username, String password, String verification) {
        AppExecutors.runNetworkIO(() -> {
            ApiResponse register = HttpUtils.getInstance().getService(CommonApiService.class)
                    .registerBySmsCode(new RegisterBySmsCodeDto(username, password, username, verification));
            // 900 token 过期
            if (register.success()) {
                EventBus.getDefault().post(new RegisterLoginEvent().setSuccess(register.success()));
            } else if(Constant.TOKEN_CODE == register.getCode()){
                EventBus.getDefault().post(new TokenEvent());
            } else{
                EventBus.getDefault().post(new RegisterLoginEvent().setSuccess(register.success()).setMsg(register.getMessage()));
            }
        });
    }

    public static void getVerificationCode(SendSmsCodeDto sendSmsCodeDto) {
        AppExecutors.runNetworkIO(() -> {
            ApiResponse response = HttpUtils.getInstance().getService(CommonApiService.class)
                    .sendSmsCode(sendSmsCodeDto);
            // 900 token 过期
            if (response != null) {
                EventBus.getDefault().post(new SendSMSEvent().setSuccess(response.success()).setMsg(response.getMessage()));
            } else if(Constant.TOKEN_CODE == response.getCode()){
                EventBus.getDefault().post(new TokenEvent());
            } else{
                EventBus.getDefault().post(new SendSMSEvent().setSuccess(false).setMsg("连接失败，请重试！"));
            }
        });
    }

    public static void resetPassword(String username, String userid, String password, String newpassword) {
        AppExecutors.runNetworkIO(() -> {
            ApiResponse register = HttpUtils.getInstance().getService(CommonApiService.class)
                    .changePassword(new ChangePasswordDto(username, userid, password, newpassword));
            // 900 token 过期
            if (register.success()) {
                EventBus.getDefault().post(new ResetPasswordEvent().setSucceed(true));
            } else if (Constant.TOKEN_CODE == register.getCode()) {
//                EventBus.getDefault().post(new TokenEvent());
            } else {
                EventBus.getDefault().post(new ResetPasswordEvent().setSucceed(false));
            }
        });
    }
}
