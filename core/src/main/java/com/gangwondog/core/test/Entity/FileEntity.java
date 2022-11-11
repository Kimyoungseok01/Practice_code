package com.gangwondog.core.test.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.Instant;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Table(name = "tb_file")
@Entity
public class FileEntity {

  @Id
  @Column(name = "id", columnDefinition = "INT UNSIGNED")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "use_at")
  private String useAt;

  @Column(name = "create_date")
  private Instant createDate;
//
//  @JsonBackReference
//  @OneToMany(fetch = FetchType.LAZY)
//  @JoinColumn(name = "id")
//  private List<UserEntity> users;
//
//  @JsonBackReference
//  @OneToMany(fetch = FetchType.LAZY)
//  @JoinColumn(name = "id")
//  private List<FileDetailEntity> fileDetails;
}
