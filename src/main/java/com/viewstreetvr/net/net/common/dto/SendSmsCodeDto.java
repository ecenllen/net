package com.viewstreetvr.net.net.common.dto;


import com.viewstreetvr.net.net.BaseDto;

public class SendSmsCodeDto extends BaseDto {
    private String phoneNumber;

    public SendSmsCodeDto(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}