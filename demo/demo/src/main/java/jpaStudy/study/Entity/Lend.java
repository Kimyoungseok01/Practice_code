package jpaStudy.study.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import jpaStudy.study.Enum.LendStatus;
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
@Entity
@Table(name = "lend")
public class Lend {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private Instant startOn;

  @Column
  private Instant dueOn;

  @Enumerated(EnumType.ORDINAL)
  private LendStatus status;

  @ManyToOne
  @JoinColumn(name = "book_id")
  @JsonManagedReference
  private Book book;

  @ManyToOne
  @JoinColumn(name = "member_id")
  @JsonManagedReference
  Member member;
}