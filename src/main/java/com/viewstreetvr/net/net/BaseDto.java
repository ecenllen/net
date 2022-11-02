package com.viewstreetvr.net.net;

import android.os.Build;

import com.viewstreetvr.net.net.util.PublicUtil;

import java.util.UUID;


public class BaseDto {
    public String agencyChannel = PublicUtil.metadata(NetApplication.appContext, "AGENCY_CHANNEL"); //代理渠道,NOT NULL
    public String appMarket = PublicUtil.metadata(NetApplication.appContext, "UMENG_CHANNEL"); //应用市场,NOT NULL
    public String appPackage = PublicUtil.getAppPackage(NetApplication.appContext);  //应用包名
    public String appName = PublicUtil.getAppName(NetApplication.appContext); //应用名称
    public String appVersion = PublicUtil.getAppInfo(NetApplication.appContext).versionName; //应用版本
    public int appVersionCode = PublicUtil.getVersionCode(NetApplication.appContext); //应用版本号
    public String deviceName = Build.MODEL;  //设备名称
    public String deviceBrand = Build.BRAND; //品牌
    public String deviceManufacturer = Build.MANUFACTURER; //设备制造商
    //    public String deviceFingerPrint = getUniqueId();// 设备的唯一标识
    public String devicePlatform = "ANDROID";   //设备平台
    public String application = "BEIDOU";   //对应项目(不同项目需要修改)
//    public String application =  PublicUtil.metadata(NetApplication.appContext, "application");   //对应项目(不同项目需要修改)

    public static String getUniqueId() {

        String serial = "serial";

        String m_szDevIDShort = "35" +
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +

                Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +

                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +

                Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +

                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +

                Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +

                Build.USER.length() % 10; //13 位

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            } else {
                serial = Build.SERIAL;
            }
            //API>=9 使用serial号
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            //serial需要一个初始化
            serial = "serial"; // 随便一个初始化
        }
        //使用硬件信息拼凑出来的15位号码
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }

}