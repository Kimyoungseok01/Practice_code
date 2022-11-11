package com.gangwondog.core.test.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name = "tb_place")
@Entity
public class PlaceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", columnDefinition = "INT UNSIGNED")
  private Long id;

  @Column(name = "content_seq", columnDefinition = "INT UNSIGNED")
  private Long contentSeq;

  @JoinColumn(name = "area_code")
  @ManyToOne
  private CommonCodeEntity areaCode;

  @JoinColumn(name = "part_code")
  @ManyToOne
  private CommonCodeEntity partCode;

  @Column(name = "title")
  private String title;

  @Column(name = "address")
  private String address;

  @Column(name = "latitude")
  private String latitude;

  @Column(name = "longitude")
  private String longitude;

  @Column(name = "tel")
  private String tel;

  @Column(name = "keyword")
  private String keyword;

  @Column(name = "used_time")
  private String usedTime;

  @Column(name = "home_page")
  private String homePage;

  @Column(name = "content", columnDefinition = "TEXT")
  private String content;

  @JoinColumn(name = "file_id")
  @ManyToOne
  private FileEntity fileId;

  @Column(name = "provision_supply")
  private String provisionSupply;

  @Column(name = "pet_facility")
  private String petFacility;

  @Column(name = "restaurant")
  private String restaurant;

  @Column(name = "parking_log")
  private String parkingLog;

  @Column(name = "main_facility")
  private String mainFacility;

  @Column(name = "used_cost",columnDefinition = "TEXT")
  private String usedCost;

  @Column(name = "policy_cautions" ,columnDefinition = "TEXT")
  private String policyCautions;

  @Column(name = "emergency_response")
  private String emergencyResponse;

  @Column(name = "memo", columnDefinition = "TEXT")
  private String memo;

  @Column(name = "bath_flag")
  private String bathFlag;

  @Column(name = "provision_flag")
  private String provisionFlag;

  @Column(name = "pet_flag")
  private String petFlag;

  @Column(name = "pet_weight")
  private Integer petWeight;

  @Column(name = "dog_breed")
  private String dogBreed;

  @Column(name = "emergency_flag")
  private String emergencyFlag;

  @Column(name = "entrance_flag")
  private String entranceFlag;

  @Column(name = "parking_flag")
  private String parkingFlag;

  @Column(name = "in_out_flag")
  private String inOutFlag;

  @Column(name = "message")
  private String message;

  @Column(name = "read_count")
  private Integer readCount;

  @Column(name = "own_at")
  private String ownAt;

  @Column(name = "create_date")
  private Instant createDate;

  @Builder
  public PlaceEntity(Long contentSeq, CommonCodeEntity areaCode, CommonCodeEntity partCode,
      String title, String address, String latitude, String longitude, String tel, String keyword,
      String usedTime, String homePage, String content, FileEntity fileId, String provisionSupply,
      String petFacility, String restaurant, String parkingLog, String mainFacility,
      String usedCost,
      String policyCautions, String emergencyResponse, String memo, String bathFlag,
      String provisionFlag, String petFlag, Integer petWeight, String dogBreed,
      String emergencyFlag,
      String entranceFlag, String parkingFlag, String inOutFlag, String message, Integer readCount,
      String ownAt, Instant createDate) {
    this.contentSeq = contentSeq;
    this.areaCode = areaCode;
    this.partCode = partCode;
    this.title = title;
    this.address = address;
    this.latitude = latitude;
    this.longitude = longitude;
    this.tel = tel;
    this.keyword = keyword;
    this.usedTime = usedTime;
    this.homePage = homePage;
    this.content = content;
    this.fileId = fileId;
    this.provisionSupply = provisionSupply;
    this.petFacility = petFacility;
    this.restaurant = restaurant;
    this.parkingLog = parkingLog;
    this.mainFacility = mainFacility;
    this.usedCost = usedCost;
    this.policyCautions = policyCautions;
    this.emergencyResponse = emergencyResponse;
    this.memo = memo;
    this.bathFlag = bathFlag;
    this.provisionFlag = provisionFlag;
    this.petFlag = petFlag;
    this.petWeight = petWeight;
    this.dogBreed = dogBreed;
    this.emergencyFlag = emergencyFlag;
    this.entranceFlag = entranceFlag;
    this.parkingFlag = parkingFlag;
    this.inOutFlag = inOutFlag;
    this.message = message;
    this.readCount = readCount;
    this.ownAt = ownAt;
    this.createDate = createDate;
  }
}
