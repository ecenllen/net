package com.viewstreetvr.net.net.common.vo;


import com.viewstreetvr.net.net.constants.PayTypeEnum;

public class ConfirmOrderVO {
    private String orderNo;
    private String paymentData;
    private PayTypeEnum payType;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(String paymentData) {
        this.paymentData = paymentData;
    }

    public PayTypeEnum getPayType() {
        return payType;
    }

    public void setPayType(PayTypeEnum payType) {
        this.payType = payType;
    }
}
