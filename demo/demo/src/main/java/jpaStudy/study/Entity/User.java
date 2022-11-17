package jpaStudy.study.Entity;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
//@Setter

//@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "user")
@Builder
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @Column(nullable = false)
  private String userId;

  @Column(nullable = false)
  private String nickname;

  @Column
  private Integer profileImageId;

  @Column(nullable = false)
  private String snsTypeCode;

  @Column(nullable = false)
  private String snsId;

  @Column(nullable = false)
  private String authCode;

  @Column(nullable = false)
  private String secessionAt;

  @Column(nullable = false)
  private Timestamp createDate;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  List<Review> reviews;


}


