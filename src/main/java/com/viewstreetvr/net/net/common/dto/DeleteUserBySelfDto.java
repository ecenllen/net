package com.viewstreetvr.net.net.common.dto;


import com.viewstreetvr.net.net.BaseDto;

/**
 * @Author: liaohaiping
 * @Description:
 * @Date: Created in 2019/6/10 0010 17:56
 */
public class DeleteUserBySelfDto extends BaseDto {
  private String password;

  public DeleteUserBySelfDto(String password) {
    this.password = password;
  }
}
