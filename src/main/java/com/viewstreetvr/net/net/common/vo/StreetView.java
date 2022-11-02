package com.viewstreetvr.net.net.common.vo;

import java.util.List;

public class StreetView {
    private long id;
    private String title;
    private String intro;
    private String description;
    private boolean homeTown;
    private String poster;
    private double longitude;
    private double latitude;
    private boolean hasPano;
    private String panoId;
    private String panoType;
    private List<String> posters;
    private String url;
    private boolean vip;
    private int viewType;

    public int getViewType() {
        return viewType;
    }

    public StreetView setViewType(int viewType) {
        this.viewType = viewType;
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHomeTown() {
        return homeTown;
    }

    public void setHomeTown(boolean homeTown) {
        this.homeTown = homeTown;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public boolean isHasPano() {
        return hasPano;
    }

    public void setHasPano(boolean hasPano) {
        this.hasPano = hasPano;
    }

    public String getPanoId() {
        return panoId;
    }

    public void setPanoId(String panoId) {
        this.panoId = panoId;
    }

    public String getPanoType() {
        return panoType;
    }

    public void setPanoType(String panoType) {
        this.panoType = panoType;
    }

    public List<String> getPosters() {
        return posters;
    }

    public void setPosters(List<String> posters) {
        this.posters = posters;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }
}
