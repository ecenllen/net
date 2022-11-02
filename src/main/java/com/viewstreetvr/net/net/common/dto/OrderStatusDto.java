package com.viewstreetvr.net.net.common.dto;


import com.viewstreetvr.net.net.BaseDto;

public class OrderStatusDto extends BaseDto {
    public String orderNo;

    public OrderStatusDto(String orderNo) {
        this.orderNo = orderNo;
    }
}
