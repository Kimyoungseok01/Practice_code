package com.gangwondog.core.test.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.Instant;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Table(name = "tb_user")
@Entity
public class UserEntity {

    @Id
    @Column(name = "id", columnDefinition = "INT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "nickname")
    private String nickname;

//    @JsonBackReference
//    @Column(name = "profile_image_id")
//    @OneToMany(mappedBy = "file",
//        fetch = FetchType.LAZY,cascade = CascadeType.ALL)

    @JoinColumn(name = "profile_image_id")
    @ManyToOne
    private FileEntity profileImageId;

    @JoinColumn(name = "sns_type_code")
    @ManyToOne
    private CommonCodeEntity snsTypeCode;

    @Column(name = "sns_id")
    private String snsId;

    @JoinColumn(name = "auth_code")
    @ManyToOne
    private CommonCodeEntity authCode;

    @Column(name = "secession_at")
    private String secessionAt;

    @Column(name = "create_date")
    private Instant createDate;
}
