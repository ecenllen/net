package com.viewstreetvr.net.net.common.vo;


import android.os.Parcel;

import com.viewstreetvr.net.net.common.dto.OpenTypeEnum;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


/**
 * 街景
 */
public class ScenicSpotVO implements Serializable {
    private long id;
    private String title;
    private String description;
    private double longitude;
    private double latitude;
    private boolean vip; //是否需要vip
    private String url;
    private String poster;
    private String panoId;
    private boolean international;
    private long countryId;
    private long provinceId;
    private OpenTypeEnum openType; //location, url,
    private String tags; // street,scenic,
    private boolean userUpload;  //是否用户上传
    private List<ScenicSpotPosterVO> posters;

    public ScenicSpotVO(){};

    protected ScenicSpotVO(Parcel in) {
        id = in.readLong();
        title = in.readString();
        description = in.readString();
        longitude = in.readDouble();
        latitude = in.readDouble();
        vip = in.readByte() != 0;
        url = in.readString();
        poster = in.readString();
        panoId = in.readString();
        international = in.readByte() != 0;
        countryId = in.readLong();
        provinceId = in.readLong();
        tags = in.readString();
        userUpload = in.readByte() != 0;
    }


    public long getId() {
        return id;
    }

    public ScenicSpotVO setId(long id) {
        this.id = id;
        return this;
    }

    public String getPanoId() {
        return panoId;
    }

    public ScenicSpotVO setPanoId(String panoId) {
        this.panoId = panoId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ScenicSpotVO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ScenicSpotVO setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public ScenicSpotVO setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public ScenicSpotVO setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public boolean isVip() {
        return vip;
    }

    public ScenicSpotVO setVip(boolean vip) {
        this.vip = vip;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ScenicSpotVO setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPoster() {
        return poster;
    }

    public ScenicSpotVO setPoster(String poster) {
        this.poster = poster;
        return this;
    }

    public boolean isInternational() {
        return international;
    }

    public ScenicSpotVO setInternational(boolean international) {
        this.international = international;
        return this;
    }

    public long getCountryId() {
        return countryId;
    }

    public ScenicSpotVO setCountryId(long countryId) {
        this.countryId = countryId;
        return this;
    }

    public long getProvinceId() {
        return provinceId;
    }

    public ScenicSpotVO setProvinceId(long provinceId) {
        this.provinceId = provinceId;
        return this;
    }

    public OpenTypeEnum getOpenType() {
        return openType;
    }

    public ScenicSpotVO setOpenType(OpenTypeEnum openType) {
        this.openType = openType;
        return this;
    }

    public String getTags() {
        return tags;
    }

    public ScenicSpotVO setTags(String tags) {
        this.tags = tags;
        return this;
    }

    public boolean isUserUpload() {
        return userUpload;
    }

    public ScenicSpotVO setUserUpload(boolean userUpload) {
        this.userUpload = userUpload;
        return this;
    }

    public List<ScenicSpotPosterVO> getPosters() {
        return posters;
    }

    public ScenicSpotVO setPosters(List<ScenicSpotPosterVO> posters) {
        this.posters = posters;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScenicSpotVO that = (ScenicSpotVO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
