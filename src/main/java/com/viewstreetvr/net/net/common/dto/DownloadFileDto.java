package com.viewstreetvr.net.net.common.dto;


import com.viewstreetvr.net.net.BaseDto;

/**
 * @Author: liaohaiping
 * @Description:
 * @Date: Created in 2019/6/20 0020 11:34
 */
public class DownloadFileDto extends BaseDto {
    public long fileId;

    public DownloadFileDto(long fileId) {
        this.fileId = fileId;
    }
}
