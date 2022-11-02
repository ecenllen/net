package com.viewstreetvr.net.net.common.dto;


import com.viewstreetvr.net.net.BaseDto;

public class RegisterUserDto extends BaseDto {
    public String userName;
    public String password;

    public RegisterUserDto() {
    }

    public RegisterUserDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}