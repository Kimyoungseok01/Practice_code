package com.gangwondog.core.test.vo;

import lombok.Data;

@Data
public class LocalVo {

  private int contentSeq;
  private String areaName;
  private String partName;
  private String title;
  private String address;
  private String latitude;
  private String longitude;
  private String tel;

  public LocalVo(int contentSeq, String areaName, String partName, String title, String address,
      String latitude, String longitude, String tel) {
    this.contentSeq = contentSeq;
    this.areaName = areaName;
    this.partName = partName;
    this.title = title;
    this.address = address;
    this.latitude = latitude;
    this.longitude = longitude;
    this.tel = tel;
  }

  public LocalVo() {

  }

  public int getContentSeq() {
    return contentSeq;
  }

  public void setContentSeq(int contentSeq) {
    this.contentSeq = contentSeq;
  }

  public String getAreaName() {
    return areaName;
  }

  public void setAreaName(String areaName) {
    this.areaName = areaName;
  }

  public String getPartName() {
    return partName;
  }

  public void setPartName(String partName) {
    this.partName = partName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }
}
