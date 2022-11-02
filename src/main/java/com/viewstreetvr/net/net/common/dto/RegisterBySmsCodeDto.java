package com.viewstreetvr.net.net.common.dto;


import com.viewstreetvr.net.net.BaseDto;

public class RegisterBySmsCodeDto extends BaseDto {
    private String userName;
    private String password;
    private String phoneNumber;
    private String verificationCode;

    public RegisterBySmsCodeDto(String userName, String password, String phoneNumber, String verificationCode) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.verificationCode = verificationCode;
    }
}