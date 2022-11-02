package com.viewstreetvr.net.net.common.vo;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * 国家（境外）
 */
public class CountryVO implements Parcelable {
  private long id;
  private String name;
  private String shortName;
  private String code;
  private String picimage;
  private int scenicCount; //景点数
  private boolean popular; //是否热门
  private String description;

  public CountryVO() {

  }

  protected CountryVO(Parcel in) {
    id = in.readLong();
    name = in.readString();
    shortName = in.readString();
    code = in.readString();
    picimage = in.readString();
    scenicCount = in.readInt();
    popular = in.readByte() != 0;
    description = in.readString();
  }

  public static final Creator<CountryVO> CREATOR = new Creator<CountryVO>() {
    @Override
    public CountryVO createFromParcel(Parcel in) {
      return new CountryVO(in);
    }

    @Override
    public CountryVO[] newArray(int size) {
      return new CountryVO[size];
    }
  };

  public long getId() {
    return id;
  }

  public CountryVO setId(long id) {
    this.id = id;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public CountryVO setDescription(String description) {
    this.description = description;
    return this;
  }

  public String getName() {
    return name;
  }

  public CountryVO setName(String name) {
    this.name = name;
    return this;
  }

  public String getShortName() {
    return shortName;
  }

  public CountryVO setShortName(String shortName) {
    this.shortName = shortName;
    return this;
  }

  public String getCode() {
    return code;
  }

  public CountryVO setCode(String code) {
    this.code = code;
    return this;
  }

  public String getPicimage() {
    return picimage;
  }

  public CountryVO setPicimage(String picimage) {
    this.picimage = picimage;
    return this;
  }

  public int getScenicCount() {
    return scenicCount;
  }

  public CountryVO setScenicCount(int scenicCount) {
    this.scenicCount = scenicCount;
    return this;
  }

  public boolean isPopular() {
    return popular;
  }

  public CountryVO setPopular(boolean popular) {
    this.popular = popular;
    return this;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeLong(id);
    dest.writeString(name);
    dest.writeString(shortName);
    dest.writeString(code);
    dest.writeString(picimage);
    dest.writeInt(scenicCount);
    dest.writeByte((byte) (popular ? 1 : 0));
    dest.writeString(description);
  }
}
