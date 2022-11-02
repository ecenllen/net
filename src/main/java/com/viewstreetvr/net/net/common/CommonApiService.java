package com.viewstreetvr.net.net.common;


import com.viewstreetvr.net.net.ApiResponse;
import com.viewstreetvr.net.net.BaseDto;
import com.viewstreetvr.net.net.DataResponse;
import com.viewstreetvr.net.net.HttpUtils;
import com.viewstreetvr.net.net.PagedList;
import com.viewstreetvr.net.net.common.dto.ApplicationDto;
import com.viewstreetvr.net.net.common.dto.ChangePasswordDto;
import com.viewstreetvr.net.net.common.dto.ConfirmOrderDto;
import com.viewstreetvr.net.net.common.dto.DashangListDto;
import com.viewstreetvr.net.net.common.dto.DeleteUserBySelfDto;
import com.viewstreetvr.net.net.common.dto.DownloadFileDto;
import com.viewstreetvr.net.net.common.dto.MergeFeatureFromOrderDto;
import com.viewstreetvr.net.net.common.dto.OrderStatusDto;
import com.viewstreetvr.net.net.common.dto.PanoramasDto;
import com.viewstreetvr.net.net.common.dto.ProductListDto;
import com.viewstreetvr.net.net.common.dto.ProvinceListDto;
import com.viewstreetvr.net.net.common.dto.ProvinceScenicSpotsDto;
import com.viewstreetvr.net.net.common.dto.RegisterBySmsCodeDto;
import com.viewstreetvr.net.net.common.dto.RegisterUserDto;
import com.viewstreetvr.net.net.common.dto.SearchScenicSpotDto;
import com.viewstreetvr.net.net.common.dto.SendSmsCodeDto;
import com.viewstreetvr.net.net.common.vo.ConfirmOrderVO;
import com.viewstreetvr.net.net.common.vo.CountryVO;
import com.viewstreetvr.net.net.common.vo.DashangVO;
import com.viewstreetvr.net.net.common.vo.LoginVO;
import com.viewstreetvr.net.net.common.vo.OrderVO;
import com.viewstreetvr.net.net.common.vo.ProductVO;
import com.viewstreetvr.net.net.common.vo.Province;
import com.viewstreetvr.net.net.common.vo.ScenicSpotVO;
import com.viewstreetvr.net.net.common.vo.StreetView;
import com.viewstreetvr.net.net.common.vo.UserFeatureVO;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * Author: liaohaiping
 * Time: 2019-06-15
 * Description:
 */
public interface CommonApiService {

    //没有使用自定义CallAdapter的时候
//    @POST(HttpUtils.API_PREFIX+"deviceuser/newuser")
//    Call<ApiResponse> newDeviceUser(@Body BaseDto dto);

    //使用RxJava2CallAdapterFactory的时候
//    @POST(HttpUtils.API_PREFIX+"deviceuser/newuser")
//    Observable<ApiResponse> newDeviceUser(@Body BaseDto dto);

    /**
     * 新用户
     *
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX + "deviceuser/newuser")
    ApiResponse newDeviceUser(@Body ApplicationDto dto);

    /**
     * 获取配置
     *
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX + "config/configs")
    DataResponse<Map<String, String>> configs(@Body BaseDto dto);

    /**
     * 注册
     *
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX + "user/register")
    ApiResponse register(@Body RegisterUserDto dto);

    /**
     * 用户名密码登录
     *
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX + "user/login")
    DataResponse<LoginVO> login(@Body RegisterUserDto dto);

    /**
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX + "order/merge_feature_from_order")
    DataResponse<List<UserFeatureVO>> mergeFeatureFromOrder(@Body MergeFeatureFromOrderDto dto);

    /**
     * 注册用户并同时登录，如果用户已存在则直接登录
     *
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX + "user/register_login")
    DataResponse<LoginVO> registerLogin(@Body RegisterUserDto dto);

    /**
     * 注销账号
     *
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX + "user/delete_user_by_self")
    ApiResponse deleteUserBySelf(@Body DeleteUserBySelfDto dto);


    /**
     * 修改用户密码
     *
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX + "user/change_password")
    ApiResponse changePassword(@Body ChangePasswordDto dto);

    /**
     * 获取用户购买的功能
     *
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX + "user/user_features")
    DataResponse<List<UserFeatureVO>> userFeatures(@Body BaseDto dto);

    /**
     * 商品列表
     *
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX + "product/list")
    DataResponse<List<ProductVO>> productList(@Body ProductListDto dto);

    /**
     * 打赏商品列表
     *
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX + "product/list_rewards")
    DataResponse<List<ProductVO>> list_rewards(@Body BaseDto dto);

    /**
     * 获取打赏榜数据
     *
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX + "dashang/list")
    DataResponse<PagedList<DashangVO>> dashang_list(@Body DashangListDto dto);

    /**
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX + "user/add_old_vip")
    ApiResponse addOldVip(@Body BaseDto dto);

    /**
     * 下单
     *
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX + "order/confirm_order")
    DataResponse<ConfirmOrderVO> confirmOrder(@Body ConfirmOrderDto dto);

    /**
     * 订单状态
     *
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX + "order/order_status")
    DataResponse<OrderVO> orderStatus(@Body OrderStatusDto dto);


    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @Multipart
    @POST(HttpUtils.API_PREFIX + "file/upload")
    DataResponse<Long> uploadFile(@Part("file") MultipartBody.Part file);


    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @Multipart
    @POST(HttpUtils.API_PREFIX + "file/upload_forever")
    DataResponse<Long> uploadFileForever(@Part("file") MultipartBody.Part file);

    /**
     * 下传文件
     *
     * @param dto
     * @return
     */
    @Multipart
    @POST(HttpUtils.API_PREFIX + "file/download")
    Call<ResponseBody> downloadFile(@Body DownloadFileDto dto);

    /**
     * VR全景
     *
     * @return
     */
    @POST(HttpUtils.API_PREFIX + "mapvr/panoramas")
    DataResponse<PagedList<StreetView>> getPanoramas(@Body PanoramasDto dto);

    /**
     * 街景
     *
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX + "mapvr/streetviews")
    DataResponse<PagedList<StreetView>> getStreetList(@Body PanoramasDto dto);


    // 街景新接口

    /**
     * 接口： 国家列表
     * url:  mapvr/countries
     * 参数： BaseDto
     * 返回值： DataResponse<List<Country>>
     */
    @POST(HttpUtils.API_PREFIX + "mapvr/countries")
    DataResponse<List<CountryVO>> getCountryList(@Body BaseDto dto);

    /**
     * 接口： 省份列表
     * url:  mapvr/provinces
     * 参数： ProvinceListDto
     * 返回值： DataResponse<List<Province>>
     */
    @POST(HttpUtils.API_PREFIX + "mapvr/provinces")
    DataResponse<List<Province>> getProvinceList(@Body ProvinceListDto dto);

    /**
     * 接口： 国家下面的景点列表
     * url:  mapvr/country_scenicspots
     * 参数： ProvinceListDto
     * 返回值： DataResponse<PagedList<ScenicSpot>>
     */
    @POST(HttpUtils.API_PREFIX + "mapvr/country_scenicspots")
    DataResponse<PagedList<ScenicSpotVO>> getCountryList(@Body ProvinceListDto dto);

    /**
     * 接口： 省下面的景点列表
     * url:  mapvr/province_scenicspots
     * 参数： ProvinceScenicSpotsDto
     * 返回值： DataResponse<PagedList<ScenicSpot>>
     */
    @POST(HttpUtils.API_PREFIX + "mapvr/province_scenicspots")
    DataResponse<PagedList<ScenicSpotVO>> getProvinceScenicSpotList(@Body ProvinceScenicSpotsDto dto);

    /**
     * 接口： 搜索景点
     * url:  mapvr/search_scenicspots
     * 参数： SearchScenicSpotDto
     * 返回值： DataResponse<PagedList<ScenicSpot>>
     */
    @POST(HttpUtils.API_PREFIX + "mapvr/search_scenicspots")
    DataResponse<PagedList<ScenicSpotVO>> getSearchScenicSpotList(@Body SearchScenicSpotDto dto);

    /**
     * 带验证码的注册
     *
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX + "user/register_by_sms_code")
    ApiResponse registerBySmsCode(@Body RegisterBySmsCodeDto dto);

    /**
     * 获取验证码
     *
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX + "user/send_register_sms_code")
    ApiResponse sendSmsCode(@Body SendSmsCodeDto dto);

}
