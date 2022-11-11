package com.gangwondog.core.test.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "tb_token")
@Entity
public class TokenEntity {

    @Id
    @Column(name = "id",columnDefinition = "INT UNSIGNED")
    private Long key;

    @Column(name = "refresh_token")
    private String refreshToken;

}
