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
import net.bytebuddy.dynamic.loading.InjectionClassLoader.Strategy;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity(name = "local")
public class LocalEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  @Column(nullable = false, unique = true, length = 10)
  private long contentSeq;

  @Column(nullable = false, length = 30)
  private String areaName;

  @Column(nullable = false, length = 30)
  private String partName;

  @Column(nullable = false, length = 30)
  private String title;

  @Column(nullable = false, length = 100)
  private String address;

  @Column(nullable = false, length = 30)
  private String latitude;

  @Column(nullable = false, length = 30)
  private String longitude;

  @Column(nullable = false, length = 30)
  private String tel;
}
