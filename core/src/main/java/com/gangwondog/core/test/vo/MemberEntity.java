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

@Getter // getter 메소드 생성
@Builder // 빌더를 사용할 수 있게 함
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 무분별한 객체 생성에 대해 한번 더 체크 가능
@Entity(name="member") // 테이블 명을 작성
public class MemberEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long pid;

  @Column(nullable = false, unique = true, length = 30)
  private String username;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false, length = 30)
  private String address;

  @Column(nullable = false, length = 30)
  private String test;

  public MemberEntity(String username, String name, String address) {
    this.username = username;
    this.name = name;
    this.address = address;
  }
}
