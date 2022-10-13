package com.gangwondog.core.test.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity(name = "localDetail")
public class LocalDetailEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  @Column(nullable = false, unique = true, length = 10)
  private long contentSeq;

  @Column(length = 100)
  private String keyword;

  @Column(length = 500)
  private String usedTime;

  @Column(length = 500)
  private String homePage;

  @Column(length = 1500)
  private String content;

  @Column(length = 1500)
  private String provisionSupply;

  @Column(length = 1000)
  private String petFacility;

  @Column(length = 1000)
  private String restaurant;

  @Column(length = 100)
  private String parkingLog;

  @Column(length = 1500)
  private String mainFacility;

  @Column(length = 1000)
  private String usedCost;

  @Column(length = 1500)
  private String policyCautions;

  @Column(length = 2000)
  private String emergencyResponse;

  @Column(length = 2000)
  private String memo;

  @Column(length = 100)
  private String bathFlag;

  @Column(length = 100)
  private String provisionFlag;

  @Column(length = 100)
  private String petFlag;

  @Column(length = 100)
  private String petWeight;

  @Column(length = 100)
  private String dogBreed;

  @Column(length = 500)
  private String emergencyFlag;

  @Column(length = 100)
  private String entranceFlag;

  @Column(length = 100)
  private String parkingFlag;

  @Column(length = 100)
  private String inOutFlag;

  @Column(length = 100)
  private String message;


}
