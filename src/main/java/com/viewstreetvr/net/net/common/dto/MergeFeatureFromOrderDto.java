package com.viewstreetvr.net.net.common.dto;


import com.viewstreetvr.net.net.BaseDto;

public class MergeFeatureFromOrderDto extends BaseDto {
    public String orderNo;

    public MergeFeatureFromOrderDto(String orderNo) {
        this.orderNo = orderNo;
    }
}
