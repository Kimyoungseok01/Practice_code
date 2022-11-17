package com.gangwondog.core.test.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Table(name = "tb_file_detail")
@Entity
public class FileDetailEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", columnDefinition = "INT UNSIGNED")
  private Long id;

  @JoinColumn(name = "file_id")
  @ManyToOne
  private FileEntity fileId;

  @Column(name = "orders")
  private Integer orders;

  @Column(name = "file_name")
  private String fileName;

  @Column(name = "origin_file_name")
  private String originFileName;

  @Column(name = "url")
  private String url;

  @Column(name = "size")
  private Integer size;

  @Column(name = "own_at")
  private String ownAt;

  @Column(name = "create_date")
  private Instant createDate;

  @Builder
  public FileDetailEntity(FileEntity fileId, Integer orders, String fileName, String originFileName,
      String url, Integer size, String ownAt, Instant createDate) {
    this.fileId = fileId;
    this.orders = orders;
    this.fileName = fileName;
    //this.originFileName = originFileName;
    this.url = url;
    //this.size = size;
    this.ownAt = ownAt;
    this.createDate = createDate;
  }
}
