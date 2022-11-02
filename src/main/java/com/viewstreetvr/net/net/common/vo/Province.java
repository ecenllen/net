package com.viewstreetvr.net.net.common.vo;




/**
 * 国内省
 */
public class Province {
  private long id;
  private long countryId;
  private String name;
  private String shortName;
  private String code;
  private String picimage;
  private int scenicCount; //景点数
  private boolean popular;
  private String description;

  public long getId() {
    return id;
  }

  public Province setId(long id) {
    this.id = id;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Province setDescription(String description) {
    this.description = description;
    return this;
  }

  public long getCountryId() {
    return countryId;
  }

  public Province setCountryId(long countryId) {
    this.countryId = countryId;
    return this;
  }

  public String getName() {
    return name;
  }

  public Province setName(String name) {
    this.name = name;
    return this;
  }

  public String getShortName() {
    return shortName;
  }

  public Province setShortName(String shortName) {
    this.shortName = shortName;
    return this;
  }

  public String getCode() {
    return code;
  }

  public Province setCode(String code) {
    this.code = code;
    return this;
  }

  public String getPicimage() {
    return picimage;
  }

  public Province setPicimage(String picimage) {
    this.picimage = picimage;
    return this;
  }

  public int getScenicCount() {
    return scenicCount;
  }

  public Province setScenicCount(int scenicCount) {
    this.scenicCount = scenicCount;
    return this;
  }

  public boolean isPopular() {
    return popular;
  }

  public Province setPopular(boolean popular) {
    this.popular = popular;
    return this;
  }
}
