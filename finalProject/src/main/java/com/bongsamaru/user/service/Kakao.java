package com.bongsamaru.user.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "kakaologin")
@Data
public class Kakao {
    @Id
    @Column(name = "KAKAOID", nullable = false, length = 255)
    private String kakaoId;

    @Column(name = "EMAIL", length = 255)
    private String email;

    @Column(name = "NICKNAME", length = 255)
    private String nickname;

    @Column(name = "PROFILEIMAGEURL", length = 255)
    private String profileImageUrl;
}
